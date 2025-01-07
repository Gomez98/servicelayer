package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.config.JwtTokenProvider;
import org.llamagas.servicelayer.domain.LoginRequest;
import org.llamagas.servicelayer.domain.Users;
import org.llamagas.servicelayer.service.AuthService;
import org.llamagas.servicelayer.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        return null;
    }
}

