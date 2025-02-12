package org.llamagas.servicelayer.controller;

import jakarta.validation.Valid;
import org.llamagas.servicelayer.anotation.CurrentUser;
import org.llamagas.servicelayer.model.request.CreateGoalHeaderRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
import org.llamagas.servicelayer.service.GoalHeaderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals/header")
public class GoalHeaderController {

    private final GoalHeaderService goalHeaderService;

    GoalHeaderController(GoalHeaderService goalHeaderService) {
        this.goalHeaderService = goalHeaderService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('PERMISO_VER_METAS')")
    public ResponseEntity<GeneralResponse> readAllGoals() {
        return goalHeaderService.getAllGoals();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('PERMISO_CREAR_METAS')")
    public ResponseEntity<GeneralResponse> createGoals(@RequestBody @Valid CreateGoalHeaderRequest request,
                                                       @CurrentUser User user) {
        return goalHeaderService.addGoalHeader(request, user);
    }
}
