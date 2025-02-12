package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.domain.Role;
import org.llamagas.servicelayer.model.domain.Permission;
import org.llamagas.servicelayer.model.request.CreateRoleRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.repository.RoleRepository;
import org.llamagas.servicelayer.repository.PermissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    /**
     * Obtener todos los roles.
     */
    public ResponseEntity<GeneralResponse> getAllRoles() {
            GeneralResponse response = new GeneralResponse();
        List<Role> roles = roleRepository.findAll();

        if (roles.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(roles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Buscar un rol por su nombre.
     */
    @Transactional(readOnly = true)
    public ResponseEntity<GeneralResponse> getRoleByName(String name) {
        GeneralResponse response = new GeneralResponse();
        Optional<Role> roleOpt = roleRepository.findByName(name.toUpperCase());

        if (roleOpt.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(roleOpt.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Crear un nuevo rol con permisos asignados.
     */
    public ResponseEntity<GeneralResponse> createRole(CreateRoleRequest request) {
        GeneralResponse response = new GeneralResponse();
        Optional<Role> existingRole = roleRepository.findByName(request.getName().toUpperCase());

        if (existingRole.isPresent()) {
            response.setCode(ResponsesCodes.ENTITY_ALREADY_EXITS.getCode());
            response.setMessage(ResponsesCodes.ENTITY_ALREADY_EXITS.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Set<Permission> permissions = new HashSet<>();
        if (request.getPermissions() != null) {
            for (Permission permission : request.getPermissions()) {
                Optional<Permission> existingPermission = permissionRepository
                        .findByName(permission.getName().toUpperCase());

                existingPermission.ifPresent(permissions::add);
            }
        }

        Role newRole = new Role();
        newRole.setId(UUID.randomUUID().toString());
        newRole.setName(request.getName().toUpperCase());
        newRole.setPermissions(permissions);
        Role savedRole = roleRepository.save(newRole);

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(savedRole);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Agregar permisos a un rol existente.
     */
    public ResponseEntity<GeneralResponse> addPermissionsToRole(String roleId, Set<String> permissionNames) {
        GeneralResponse response = new GeneralResponse();
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (roleOpt.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage("El rol no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Role role = roleOpt.get();
        Set<Permission> permissions = new HashSet<>();

        for (String permissionName : permissionNames) {
            Optional<Permission> permissionOpt = permissionRepository.findByName(permissionName);
            if (permissionOpt.isPresent()) {
                permissions.add(permissionOpt.get());
            } else {
                response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
                response.setMessage("Permiso no encontrado: " + permissionName);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }

        role.getPermissions().addAll(permissions);
        Role updatedRole = roleRepository.save(role);

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage("Permisos agregados correctamente al rol.");
        response.setData(updatedRole);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Eliminar un permiso de un rol.
     */
    public ResponseEntity<GeneralResponse> removePermissionFromRole(String roleId, String permissionName) {
        GeneralResponse response = new GeneralResponse();
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (roleOpt.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage("El rol no existe.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Role role = roleOpt.get();
        boolean removed = role.getPermissions().removeIf(permission -> permission.getName().equals(permissionName));

        if (!removed) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage("Permiso no encontrado en el rol.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Role updatedRole = roleRepository.save(role);

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage("Permiso eliminado del rol.");
        response.setData(updatedRole);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
