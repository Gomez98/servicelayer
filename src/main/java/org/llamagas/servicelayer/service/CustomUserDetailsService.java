package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.domain.Users;
import org.llamagas.servicelayer.model.response.GeneralResponse;
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
        ResponseEntity<GeneralResponse> response = usersService.getByUsername(username);
        if (response.getBody().getCode().equalsIgnoreCase(ResponsesCodes.OBJECT_NOT_FOUND.getCode())) {
            throw new UsernameNotFoundException(response.getBody().getMessage());
        }

        Users user = (Users) response.getBody().getData();
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}




