package org.llamagas.servicelayer.controller;

import jakarta.validation.Valid;
import org.llamagas.servicelayer.model.request.CreatePermissionRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * Obtener todos los permisos.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('PERMISO_VER_PERMISOS')")
    public ResponseEntity<GeneralResponse> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    /**
     * Crear un nuevo permiso.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('PERMISO_CREAR_PERMISOS')")
    public ResponseEntity<GeneralResponse> createPermission(@RequestBody @Valid CreatePermissionRequest request) {
        return permissionService.createPermission(request);
    }

    /**
     * Eliminar un permiso por su nombre.
     */
    @DeleteMapping("/{permissionName}")
    @PreAuthorize("hasAuthority('PERMISO_ELIMINAR_PERMISOS')")
    public ResponseEntity<GeneralResponse> deletePermission(@PathVariable String permissionName) {
        return permissionService.deletePermission(permissionName);
    }
}
