package org.llamagas.servicelayer.controller;

import jakarta.validation.Valid;
import org.llamagas.servicelayer.anotation.CurrentUser;
import org.llamagas.servicelayer.model.request.CreateGoalHeaderRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.service.GoalHeaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goals/header")
public class GoalHeaderController {

    private final GoalHeaderService goalHeaderService;

    GoalHeaderController(GoalHeaderService goalHeaderService) {
        this.goalHeaderService = goalHeaderService;
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> readAllGoals() {
        return goalHeaderService.getAllGoals();
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createGoals(@RequestBody @Valid CreateGoalHeaderRequest request,
                                                       @CurrentUser User user) {
        return goalHeaderService.addGoalHeader(request, user);
    }
}
