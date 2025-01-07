package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.domain.GoalDetail;
import org.llamagas.servicelayer.service.GoalDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals/detail")
public class GoalDetailController {

    private final GoalDetailService goalDetailService;

    public GoalDetailController(GoalDetailService goalDetailService) {
        this.goalDetailService = goalDetailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGoalDetail(@PathVariable String id) {
        return goalDetailService.getGoalDetail(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createGoalDetail(@RequestBody GoalDetail goalDetail) {
        return goalDetailService.createGoalDetail(goalDetail);
    }

}
