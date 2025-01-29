package org.llamagas.servicelayer.service;


import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.domain.GoalDetail;
import org.llamagas.servicelayer.model.domain.GoalHeader;
import org.llamagas.servicelayer.model.request.CreateGoalDetailRequest;
import org.llamagas.servicelayer.model.request.UpdateGoalDetailRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.repository.GoalDetailRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GoalDetailService {

    private final GoalDetailRepository goalDetailRepository;

    GoalDetailService(GoalDetailRepository goalDetailRepository) {
        this.goalDetailRepository = goalDetailRepository;
    }

    public ResponseEntity<GeneralResponse> getGoalDetail(String goalHeaderId) {
        GeneralResponse response = new GeneralResponse();
        Optional<GoalDetail> goalDetailOptional = goalDetailRepository.findByGoalHeader_Id(goalHeaderId);
        if (goalDetailOptional.isPresent()) {
            response.setData(goalDetailOptional.get());
            response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
            response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    public ResponseEntity<GeneralResponse> createGoalDetail(CreateGoalDetailRequest request) {
        GeneralResponse response = new GeneralResponse();
        GoalDetail goalDetail = new GoalDetail();

        goalDetail.setId(UUID.randomUUID().toString());

        GoalHeader goalHeader = new GoalHeader();
        goalHeader.setId(request.getGoalHeader().getId());

        goalDetail.setGoalHeader(goalHeader);
        goalDetail.setContent(request.getContent());
        GoalDetail saved = goalDetailRepository.save(goalDetail);

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(saved);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GeneralResponse> updateGoalDetail(UpdateGoalDetailRequest request) {
        GeneralResponse response = new GeneralResponse();
        Optional<GoalDetail> goalDetailOptional = goalDetailRepository.findByGoalHeader_Id(request.getId());
        if (goalDetailOptional.isEmpty()) {
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        GoalDetail goalDetail = goalDetailOptional.get();
        goalDetail.setContent(request.getContent());
        goalDetailRepository.save(goalDetail);
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
