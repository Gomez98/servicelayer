package org.llamagas.servicelayer.model.request;

import lombok.Data;

@Data
public class CreateMasterFieldRequest {
    private String name;
    private boolean active;
}
