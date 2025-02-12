package org.llamagas.servicelayer.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.llamagas.servicelayer.model.request.CreateRoleRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Obtener todos los roles con sus permisos.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('PERMISO_VER_ROLES')")
    public ResponseEntity<GeneralResponse> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * Buscar un rol por su nombre.
     */
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('PERMISO_VER_ROL_POR_NOMBRE')")
    public ResponseEntity<GeneralResponse> getRoleByName(@RequestParam @NotEmpty String name) {
        return roleService.getRoleByName(name);
    }

    /**
     * Crear un nuevo rol con permisos asignados.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('PERMISO_CREAR_ROL')")
    public ResponseEntity<GeneralResponse> createRole(@RequestBody @Valid CreateRoleRequest request) {
        return roleService.createRole(request);
    }

    /**
     * Agregar permisos a un rol.
     */
    @PostMapping("/{roleId}/permissions")
    @PreAuthorize("hasAuthority('PERMISO_AGREGAR_PERMISO_AL_ROL')")
    public ResponseEntity<GeneralResponse> addPermissionsToRole(
            @PathVariable String roleId,
            @RequestBody Set<String> permissionNames) {
        return roleService.addPermissionsToRole(roleId, permissionNames);
    }

    /**
     * Eliminar un permiso de un rol.
     */
    @DeleteMapping("/{roleId}/permissions/{permissionName}")
    @PreAuthorize("hasAuthority('PERMISO_ELIMINAR_PERMISO_AL_ROL')")
    public ResponseEntity<GeneralResponse> removePermissionFromRole(
            @PathVariable String roleId,
            @PathVariable String permissionName) {
        return roleService.removePermissionFromRole(roleId, permissionName);
    }
}
