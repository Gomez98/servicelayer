package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.domain.Users;
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

    public ResponseEntity<GeneralResponse> createUser(Users user) {
        GeneralResponse response = new GeneralResponse();

        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(usersRepository.save(user));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GeneralResponse> getUser(String username, String password) {
        GeneralResponse response = new GeneralResponse();
        Optional<Users> user = usersRepository.findByUsername(username);
        if (user.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            response.setCode(ResponsesCodes.UNAUTHORIZED.getCode());
            response.setMessage(ResponsesCodes.UNAUTHORIZED.getDescription());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        response.setData(user.get());
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GeneralResponse> getByUsername(String username) {
        GeneralResponse response = new GeneralResponse();
        Optional<Users> user = usersRepository.findByUsername(username);
        if (user.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(user.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
