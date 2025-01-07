package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.domain.MasterFields;
import org.llamagas.servicelayer.repository.MasterFieldsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MasterFieldsService {

    private final MasterFieldsRepository masterFieldsRepository;

    public MasterFieldsService(MasterFieldsRepository masterFieldsRepository) {
        this.masterFieldsRepository = masterFieldsRepository;
    }

    public ResponseEntity<?> getAllMasterFields() {
        List<MasterFields> masterFieldsList = masterFieldsRepository.findAll();
        return new ResponseEntity<>(masterFieldsList, HttpStatus.OK);
    }

    public ResponseEntity<?> createMasterField(MasterFields masterFields) {
        masterFields.setId(UUID.randomUUID().toString());
        MasterFields masterField = masterFieldsRepository.save(masterFields);
        return new ResponseEntity<>(masterField, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateMasterField(String id, MasterFields updatedField) {
        Optional<MasterFields> optionalMasterField = masterFieldsRepository.findById(id);

        if (optionalMasterField.isPresent()) {
            MasterFields existingField = optionalMasterField.get();
            existingField.setName(updatedField.getName());
            existingField.setActive(updatedField.isActive());

            MasterFields savedField = masterFieldsRepository.save(existingField);

            return new ResponseEntity<>(savedField, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Master field not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
