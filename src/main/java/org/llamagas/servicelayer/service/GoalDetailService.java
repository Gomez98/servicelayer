package org.llamagas.servicelayer.service;


import org.llamagas.servicelayer.domain.GoalDetail;
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

    public ResponseEntity<?> getGoalDetail(String goalHeaderId) {
        Optional<GoalDetail> goalDetailOptional = goalDetailRepository.findByGoalHeader_Id(goalHeaderId);

        if (goalDetailOptional.isPresent()) {
            return new ResponseEntity<>(goalDetailOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Goal detail not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createGoalDetail(GoalDetail goalDetail) {
        goalDetail.setId(UUID.randomUUID().toString());
        goalDetailRepository.save(goalDetail);
        return new ResponseEntity<>(goalDetail, HttpStatus.CREATED);
    }

}
