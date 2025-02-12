package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.config.JwtTokenProvider;
import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.request.CreateUserRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.request.LoginRequest;
import org.llamagas.servicelayer.model.domain.User;
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
        User user = (User) userResponse.getBody().getData();
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(tokenProvider.generateToken(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GeneralResponse> register(CreateUserRequest request) {
        ResponseEntity<GeneralResponse> userResponse = usersService.createUser(request);
        return new ResponseEntity<>(userResponse.getBody(), userResponse.getStatusCode());
    }
}
