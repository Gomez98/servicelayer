package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGoalHeaderRequest {
    @NotNull
    @Size(max = 500, message = "La descripcion no puede tener más de 500 caracteres")
    private String description;
}
