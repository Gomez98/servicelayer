package org.llamagas.servicelayer.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GoalHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;
    private String description;
    private String createdBy;
    private String modifiedBy;
    private String status;

}
