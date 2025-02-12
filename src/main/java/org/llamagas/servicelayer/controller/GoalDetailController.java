package org.llamagas.servicelayer.controller;

import jakarta.validation.Valid;
import org.llamagas.servicelayer.model.request.CreateGoalDetailRequest;
import org.llamagas.servicelayer.model.request.DeleteGoalDetailRequest.DeleteGoalDetailRequest;
import org.llamagas.servicelayer.model.response.GeneralResponse;
    import org.llamagas.servicelayer.model.request.UpdateGoalDetailRequest;
import org.llamagas.servicelayer.service.GoalDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals/detail")
public class GoalDetailController {

    private final GoalDetailService goalDetailService;

    public GoalDetailController(GoalDetailService goalDetailService) {
        this.goalDetailService = goalDetailService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISO_VER_DETALLE_METAS')")
    public ResponseEntity<GeneralResponse> getGoalDetail(@PathVariable String id) {
        return goalDetailService.getGoalDetail(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('PERMISO_CREAR_DETALLE_METAS')")
    public ResponseEntity<GeneralResponse> createGoalDetail(@RequestBody @Valid CreateGoalDetailRequest request) {
        return goalDetailService.createGoalDetail(request);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('PERMISO_ACTUALIZAR_DETALLE_METAS')")
    public ResponseEntity<GeneralResponse> updateGoalDetail(@RequestBody @Valid UpdateGoalDetailRequest request) {
        return goalDetailService.updateGoalDetail(request);
    }

}
