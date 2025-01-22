package org.llamagas.servicelayer.controller;

import jakarta.validation.Valid;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.request.LoginRequest;
import org.llamagas.servicelayer.model.domain.Users;
import org.llamagas.servicelayer.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        return authService.register(user);
    }
}

