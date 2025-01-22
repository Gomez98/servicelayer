package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateGoalHeaderRequest {
    @NotNull
    private String description;
}
