package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.model.request.GoalReportRequest;
import org.llamagas.servicelayer.repository.GoalReportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class GoalReportService {

    private final GoalReportRepository repository;

    public GoalReportService(GoalReportRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> getReport(GoalReportRequest request) {
        return new ResponseEntity<>(repository.getGoalReport(request.getUsername(), request.getStatus()), HttpStatus.OK);
    }
}
