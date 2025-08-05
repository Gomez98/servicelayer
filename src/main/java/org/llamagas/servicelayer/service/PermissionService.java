package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.domain.Permission;
import org.llamagas.servicelayer.model.request.CreatePermissionRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.repository.PermissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    /**
     * Obtener todos los permisos.
     */
    @Transactional(readOnly = true)
    public ResponseEntity<GeneralResponse> getAllPermissions() {
        GeneralResponse response = new GeneralResponse();
        List<Permission> permissions = permissionRepository.findAllByIds();

        if (permissions.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(permissions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Crear un nuevo permiso.
     */
    @Transactional
    public ResponseEntity<GeneralResponse> createPermission(CreatePermissionRequest request) {
        GeneralResponse response = new GeneralResponse();

        if (permissionRepository.findByName(request.getName()).isPresent()) {
            response.setCode(ResponsesCodes.ENTITY_ALREADY_EXITS.getCode());
            response.setMessage(ResponsesCodes.ENTITY_ALREADY_EXITS.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Permission newPermission = new Permission();
        newPermission.setName(request.getName());

        Permission savedPermission = permissionRepository.save(newPermission);

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(savedPermission);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Eliminar un permiso por su nombre.
     */
    @Transactional
    public ResponseEntity<GeneralResponse> deletePermission(String permissionName) {
        GeneralResponse response = new GeneralResponse();
        Optional<Permission> permissionOpt = permissionRepository.findByName(permissionName);

        if (permissionOpt.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        permissionRepository.delete(permissionOpt.get());

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
