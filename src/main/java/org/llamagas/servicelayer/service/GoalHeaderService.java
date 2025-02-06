package org.llamagas.servicelayer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.llamagas.servicelayer.constants.ResponsesCodes;
import org.llamagas.servicelayer.model.request.CreateGoalHeaderRequest;
import org.llamagas.servicelayer.model.response.AllGoalsResponse;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.model.domain.GoalHeader;
import org.llamagas.servicelayer.model.domain.GoalHeaderStatus;
import org.llamagas.servicelayer.repository.GoalHeaderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoalHeaderService {

    private final GoalHeaderRepository goalHeaderRepository;

    GoalHeaderService(GoalHeaderRepository goalHeaderRepository) {
        this.goalHeaderRepository = goalHeaderRepository;
    }

    public ResponseEntity<GeneralResponse> getAllGoals(){
        GeneralResponse response = new GeneralResponse();
        List<GoalHeader> goalHeaders = goalHeaderRepository.findAll();
        if(goalHeaders.isEmpty()){
            response.setMessage(ResponsesCodes.OBJECT_NOT_FOUND.getDescription());
            response.setCode(ResponsesCodes.OBJECT_NOT_FOUND.getCode());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        AllGoalsResponse allGoalsResponse = new AllGoalsResponse();
        allGoalsResponse.setGoalHeaders(goalHeaders);
        allGoalsResponse.setTotalGoals(goalHeaders.size());

        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(allGoalsResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GeneralResponse> addGoalHeader(CreateGoalHeaderRequest request, User user){
        GeneralResponse response = new GeneralResponse();
        GoalHeader goalHeader = new GoalHeader();
        goalHeader.setId(UUID.randomUUID().toString());
        goalHeader.setDescription(request.getDescription());
        goalHeader.setCreatedBy(user.getUsername());
        goalHeader.setModifiedBy(user.getUsername());
        goalHeader.setStatus(GoalHeaderStatus.PREPARADO.name());
        goalHeaderRepository.save(goalHeader);
        response.setCode(ResponsesCodes.SUCCESSFUL.getCode());
        response.setMessage(ResponsesCodes.SUCCESSFUL.getDescription());
        response.setData(goalHeader);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> updateGoalHeader(GoalHeader goalHeader){
        goalHeaderRepository.save(goalHeader);
        return new ResponseEntity<>(goalHeader, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteGoalHeader(GoalHeader goalHeader){
        goalHeaderRepository.delete(goalHeader);
        return new ResponseEntity<>(goalHeader, HttpStatus.OK);
    }


}
