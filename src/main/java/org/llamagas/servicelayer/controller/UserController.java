package org.llamagas.servicelayer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.llamagas.servicelayer.anotation.CurrentUser;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('PERMISO_VER_USUARIO')")
    @Operation(
            summary = "Obtener información del usuario actual",
            description = "Retorna los datos del usuario autenticado que realiza la solicitud"
    )
    public ResponseEntity<GeneralResponse> getUser(
            @Parameter(description = "Usuario autenticado", required = true)
            @CurrentUser User user) {
        return usersService.getByUsername(user.getUsername());
    }
}
