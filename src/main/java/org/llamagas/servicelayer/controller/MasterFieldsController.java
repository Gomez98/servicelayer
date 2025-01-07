package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.domain.MasterFields;
import org.llamagas.servicelayer.service.MasterFieldsService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/master-fields")
public class MasterFieldsController {

    private final MasterFieldsService masterFieldsService;

    MasterFieldsController(MasterFieldsService masterFieldsService) {
        this.masterFieldsService = masterFieldsService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllMasterFields() {
        return masterFieldsService.getAllMasterFields();
    }

    @PostMapping("/")
    public ResponseEntity<?> addMasterField(@Validated @RequestBody MasterFields masterField) {
        return masterFieldsService.createMasterField(masterField);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMasterField(@PathVariable String id, @Validated @RequestBody MasterFields updatedField) {
        return masterFieldsService.updateMasterField(id, updatedField);
    }

}
