package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreatePermissionRequest {
    @NotEmpty
    private String name;
}
