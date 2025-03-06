package org.llamagas.servicelayer.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class CreateUserRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private Set<CreateUserRoleRequest> roles;

    @Data
    public static class CreateUserRoleRequest {
        @NotEmpty
        private String name;
    }
}
