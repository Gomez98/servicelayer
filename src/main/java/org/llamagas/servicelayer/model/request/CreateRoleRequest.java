package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.llamagas.servicelayer.model.domain.Permission;

import java.util.Set;

@Data
public class CreateRoleRequest {
    @NotEmpty
    private String name;
    @NotNull
    private Set<Permission> permissions;
}
