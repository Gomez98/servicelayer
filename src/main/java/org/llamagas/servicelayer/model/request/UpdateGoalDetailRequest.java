package org.llamagas.servicelayer.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateGoalDetailRequest {
    @NotNull
    private String id;
    private String content;
}
