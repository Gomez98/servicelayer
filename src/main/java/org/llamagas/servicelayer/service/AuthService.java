package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.config.JwtTokenProvider;
import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.request.LoginRequest;
import org.llamagas.servicelayer.model.domain.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersService usersService;
    private final JwtTokenProvider tokenProvider;

    public AuthService(UsersService usersService, JwtTokenProvider tokenProvider) {
        this.usersService = usersService;
        this.tokenProvider = tokenProvider;
    }

    public ResponseEntity<GeneralResponse> login(LoginRequest request) {
        GeneralResponse response = new GeneralResponse();
        ResponseEntity<GeneralResponse> userResponse = usersService.getUser(request.getUsername(), request.getPassword());
        if (userResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            response.setCode(ResponsesCodes.UNAUTHORIZED.getCode());
            response.setMessage(ResponsesCodes.UNAUTHORIZED.getDescription());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        if(userResponse.getBody() == null || userResponse.getBody().getData() == null) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        Users user = (Users) userResponse.getBody().getData();
        response.setData(tokenProvider.generateToken(user.getUsername()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> register(Users user) {
        Users userCreated = (Users) usersService.createUser(user).getBody();
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }
}
