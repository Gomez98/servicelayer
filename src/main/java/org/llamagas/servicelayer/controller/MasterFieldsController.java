package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.model.request.CreateMasterFieldRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.request.UpdateMasterFieldRequest;
import org.llamagas.servicelayer.service.MasterFieldsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/master-fields")
public class MasterFieldsController {

    private final MasterFieldsService masterFieldsService;

    MasterFieldsController(MasterFieldsService masterFieldsService) {
        this.masterFieldsService = masterFieldsService;
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllMasterFields() {
        return masterFieldsService.getAllMasterFields();
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> addMasterField(@RequestBody CreateMasterFieldRequest request) {
        return masterFieldsService.createMasterField(request);
    }

    @PatchMapping("/update")
    public ResponseEntity<GeneralResponse> updateMasterField(@RequestBody UpdateMasterFieldRequest request) {
        return masterFieldsService.updateMasterField(request);
    }

}
