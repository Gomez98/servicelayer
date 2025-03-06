package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateMasterFieldRequest {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private boolean active;
}
