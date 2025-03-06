package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateMasterFieldRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private boolean active;
}
