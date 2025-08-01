package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.domain.Role;
import org.llamagas.servicelayer.model.domain.User;
import org.llamagas.servicelayer.model.request.CreateUserRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UsersService(UsersRepository usersRepository, RoleService roleService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.roleService = roleService;
    }


    @Transactional
    public ResponseEntity<GeneralResponse> createUser(CreateUserRequest request) {
        GeneralResponse response = new GeneralResponse();

        // Verificar si el usuario ya existe
        if (usersRepository.findByUsername(request.getUsername()).isPresent()) {
            response.setCode(ResponsesCodes.ENTITY_ALREADY_EXITS.getCode());
            response.setMessage(ResponsesCodes.ENTITY_ALREADY_EXITS.getDescription());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // Crear el usuario (sin roles)
        User newUser = new User();
        //newUser.setId(UUID.randomUUID().toString());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setName(request.getName());

        // Buscar roles y asignarlos al usuario
        Set<Role> roles = new HashSet<>();
        for (CreateUserRequest.CreateUserRoleRequest role : request.getRoles()) {
            ResponseEntity<GeneralResponse> roleResponse = roleService.getRoleByName(role.getName().toUpperCase());

            if (roleResponse.getBody() != null &&
                    roleResponse.getBody().getCode().equalsIgnoreCase(ResponsesCodes.SUCCESSFUL.getCode())) {

                Role foundRole = (Role) roleResponse.getBody().getData();
                roles.add(foundRole);
            } else {
                response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
                response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }

        newUser.setRoles(roles);
        User savedUser = usersRepository.save(newUser);

        // Respuesta exitosa
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(savedUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    public ResponseEntity<GeneralResponse> getUser(String username, String password) {
        GeneralResponse response = new GeneralResponse();
        Optional<User> user = usersRepository.findByUsername(username);
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
        Optional<User> user = usersRepository.findByUsername(username);
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
