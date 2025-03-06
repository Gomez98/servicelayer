package org.llamagas.servicelayer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Roles", description = "Operaciones relacionadas con la gestión de roles y permisos")
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
    @Operation(summary = "Obtener todos los roles", description = "Devuelve una lista de todos los roles y sus permisos asociados.")
    public ResponseEntity<GeneralResponse> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * Buscar un rol por su nombre.
     */
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('PERMISO_VER_ROL_POR_NOMBRE')")
    @Operation(summary = "Buscar un rol por nombre", description = "Permite obtener un rol específico utilizando su nombre.")
    public ResponseEntity<GeneralResponse> getRoleByName(
            @Parameter(description = "Nombre del rol a buscar", required = true)
            @RequestParam @Valid @NotEmpty String name) {
        return roleService.getRoleByName(name);
    }

    /**
     * Crear un nuevo rol con permisos asignados.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('PERMISO_CREAR_ROL')")
    @Operation(summary = "Crear un nuevo rol", description = "Crea un nuevo rol con los permisos especificados en la solicitud.")
    public ResponseEntity<GeneralResponse> createRole(
            @Parameter(description = "Datos del nuevo rol a crear", required = true)
            @RequestBody @Valid CreateRoleRequest request) {
        return roleService.createRole(request);
    }

    /**
     * Agregar permisos a un rol.
     */
    @PostMapping("/{roleId}/permissions")
    @PreAuthorize("hasAuthority('PERMISO_AGREGAR_PERMISO_AL_ROL')")
    @Operation(summary = "Agregar permisos a un rol", description = "Asigna una lista de permisos a un rol específico.")
    public ResponseEntity<GeneralResponse> addPermissionsToRole(
            @Parameter(description = "ID del rol al que se agregarán permisos", required = true)
            @PathVariable String roleId,
            @Parameter(description = "Lista de nombres de permisos a asignar", required = true)
            @RequestBody Set<String> permissionNames) {
        return roleService.addPermissionsToRole(roleId, permissionNames);
    }

    /**
     * Eliminar un permiso de un rol.
     */
    @DeleteMapping("/{roleId}/permissions/{permissionName}")
    @PreAuthorize("hasAuthority('PERMISO_ELIMINAR_PERMISO_AL_ROL')")
    @Operation(summary = "Eliminar un permiso de un rol", description = "Elimina un permiso específico de un rol determinado.")
    public ResponseEntity<GeneralResponse> removePermissionFromRole(
            @Parameter(description = "ID del rol del que se eliminará el permiso", required = true)
            @PathVariable String roleId,
            @Parameter(description = "Nombre del permiso a eliminar", required = true)
            @PathVariable String permissionName) {
        return roleService.removePermissionFromRole(roleId, permissionName);
    }
}
