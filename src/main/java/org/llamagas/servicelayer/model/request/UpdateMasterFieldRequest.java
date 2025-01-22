package org.llamagas.servicelayer.model.request;

import lombok.Data;

@Data
public class UpdateMasterFieldRequest {
    private String id;
    private String name;
    private boolean active;
}
