package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.request.CreateMasterFieldRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.domain.MasterFields;
import org.llamagas.servicelayer.model.request.UpdateMasterFieldRequest;
import org.llamagas.servicelayer.repository.MasterFieldsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MasterFieldsService {

    private final MasterFieldsRepository masterFieldsRepository;

    public MasterFieldsService(MasterFieldsRepository masterFieldsRepository) {
        this.masterFieldsRepository = masterFieldsRepository;
    }

    public ResponseEntity<GeneralResponse> getAllMasterFields() {
        GeneralResponse response = new GeneralResponse();
        List<MasterFields> masterFieldsList = masterFieldsRepository.findAll();
        if(masterFieldsList.isEmpty()){
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(masterFieldsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GeneralResponse> createMasterField(CreateMasterFieldRequest request) {
        GeneralResponse response = new GeneralResponse();

        MasterFields masterFields = new MasterFields();
        masterFields.setName(request.getName());
        masterFields.setId(UUID.randomUUID().toString());
        masterFields.setActive(true);

        MasterFields masterField = masterFieldsRepository.save(masterFields);

        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setData(masterField);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<GeneralResponse> updateMasterField(UpdateMasterFieldRequest request) {
        GeneralResponse response = new GeneralResponse();
        Optional<MasterFields> optionalMasterField = masterFieldsRepository.findById(request.getId());
        if (optionalMasterField.isPresent()) {
            MasterFields existingField = optionalMasterField.get();
            existingField.setName(request.getName());
            existingField.setActive(request.isActive());

            MasterFields savedField = masterFieldsRepository.save(existingField);
            response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
            response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
            response.setData(savedField);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
