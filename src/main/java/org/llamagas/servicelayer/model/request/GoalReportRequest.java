package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GoalReportRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String status;
}
