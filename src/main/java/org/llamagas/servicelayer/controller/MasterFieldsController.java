package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.model.request.CreateMasterFieldRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.request.UpdateMasterFieldRequest;
import org.llamagas.servicelayer.service.MasterFieldsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master-fields")
public class MasterFieldsController {

    private final MasterFieldsService masterFieldsService;

    MasterFieldsController(MasterFieldsService masterFieldsService) {
        this.masterFieldsService = masterFieldsService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('PERMISO_VER_CAMPOS_MAESTROS')")
    public ResponseEntity<GeneralResponse> getAllMasterFields() {
        return masterFieldsService.getAllMasterFields();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('PERMISO_CREAR_CAMPO_MAESTRO')")
    public ResponseEntity<GeneralResponse> addMasterField(@RequestBody CreateMasterFieldRequest request) {
        return masterFieldsService.createMasterField(request);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('PERMISO_ACTUALIZAR_CAMPO_MAESTRO')")
    public ResponseEntity<GeneralResponse> updateMasterField(@RequestBody UpdateMasterFieldRequest request) {
        return masterFieldsService.updateMasterField(request);
    }

}
