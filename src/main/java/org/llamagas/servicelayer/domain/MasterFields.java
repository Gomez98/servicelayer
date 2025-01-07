package org.llamagas.servicelayer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MasterFields {
    @Id
    private String id;
    private String name;
    private boolean active;
}

