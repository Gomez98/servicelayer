package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateGoalDetailRequest {

    @NotBlank(message = "El contenido no puede estar vacio")
    private String content;

    @NotNull
    private GoalHeader goalHeader;

    @Data
    public static class GoalHeader {
        @NotNull(message = "El ID no puede ser nulo")
        private String id;

    }
}
