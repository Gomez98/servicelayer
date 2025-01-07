package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.domain.GoalHeader;
import org.llamagas.servicelayer.domain.GoalHeaderStatus;
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

    public ResponseEntity<?> getAllGoals(){
        List<GoalHeader> goalHeaders = goalHeaderRepository.findAll();
        if(goalHeaders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(goalHeaders, HttpStatus.OK);
    }

    public ResponseEntity<?> addGoalHeader(GoalHeader goalHeader, User user){
        goalHeader.setId(UUID.randomUUID().toString());
        goalHeader.setDescription(goalHeader.getDescription().trim());
        goalHeader.setCreatedBy(user.getUsername());
        goalHeader.setModifiedBy(user.getUsername());
        goalHeader.setStatus(GoalHeaderStatus.PREPARADO.name());
        goalHeaderRepository.save(goalHeader);
        return new ResponseEntity<>(goalHeader, HttpStatus.OK);
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
