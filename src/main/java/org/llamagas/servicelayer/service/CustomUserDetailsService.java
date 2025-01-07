package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.domain.LoginRequest;
import org.llamagas.servicelayer.domain.Users;
import org.llamagas.servicelayer.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersService usersService;

    public CustomUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<?> response = usersService.getByUsername(username);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Users user = (Users) response.getBody();
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}




