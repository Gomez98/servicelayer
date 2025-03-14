package org.llamagas.servicelayer.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MasterFields {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;
    private String name;
    private boolean active;
}

