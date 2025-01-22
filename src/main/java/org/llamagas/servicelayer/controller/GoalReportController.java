package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.model.request.GoalReportRequest;
import org.llamagas.servicelayer.service.GoalReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goals/report")
public class GoalReportController {

    private final GoalReportService service;

    public GoalReportController(GoalReportService service) {
        this.service = service;
    }

    @PostMapping("/general")
    public ResponseEntity<?> getReport(@RequestBody GoalReportRequest request) {
        return service.getReport(request);
    }
}
