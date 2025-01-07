package org.llamagas.servicelayer.controller;

import org.llamagas.servicelayer.anotation.CurrentUser;
import org.llamagas.servicelayer.domain.GoalHeader;
import org.llamagas.servicelayer.service.GoalHeaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals/header")
public class GoalHeaderController {

    private final GoalHeaderService goalHeaderService;

    GoalHeaderController(GoalHeaderService goalHeaderService) {
        this.goalHeaderService = goalHeaderService;
    }


    @GetMapping("/")
    public ResponseEntity<?> readAllGoals() {
        return goalHeaderService.getAllGoals();
    }

    @PostMapping("/")
    public ResponseEntity<?> createGoals(@RequestBody GoalHeader goalHeader, @CurrentUser User user) {

        return goalHeaderService.addGoalHeader(goalHeader, user);
    }

}
