package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.model.request.GoalReportRequest;
import org.llamagas.servicelayer.service.GoalReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals/report")
public class GoalReportController {

    private final GoalReportService service;

    public GoalReportController(GoalReportService service) {
        this.service = service;
    }

    @PostMapping("/general")
    @PreAuthorize("hasAuthority('PERMISO_VER_REPORTE')")
    public ResponseEntity<?> getReport(@RequestBody GoalReportRequest request) {
        return service.getReport(request);
    }
}
