package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.config.JwtTokenProvider;
import org.llamagas.servicelayer.domain.LoginRequest;
import org.llamagas.servicelayer.domain.LoginResponse;
import org.llamagas.servicelayer.domain.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UsersService usersService;
    private final JwtTokenProvider tokenProvider;

    public AuthService(UsersService usersService, JwtTokenProvider tokenProvider) {
        this.usersService = usersService;
        this.tokenProvider = tokenProvider;
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        ResponseEntity<?> response = usersService.getUser(loginRequest);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Users user = (Users) response.getBody();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(tokenProvider.generateToken(user.getUsername(), user.getPassword()));
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);

    }
}
