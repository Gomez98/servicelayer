package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.domain.LoginRequest;
import org.llamagas.servicelayer.domain.Users;
import org.llamagas.servicelayer.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> createUser(Users user) {
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<?> getUser(LoginRequest loginRequest) {
        Optional<Users> user = usersRepository.findByUsername(loginRequest.getUsername());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    public ResponseEntity<?> getByUsername(String username) {
        Optional<Users> user = usersRepository.findByUsername(username);
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
