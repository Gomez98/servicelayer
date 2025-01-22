package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.anotation.CurrentUser;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public ResponseEntity<GeneralResponse> getUser(@CurrentUser User user) {
        return usersService.getByUsername(user.getUsername());
    }
}
