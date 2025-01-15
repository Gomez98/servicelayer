package org.llamagas.servicelayer.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GoalHeader {
    @Id
    private String id;
    private String description;
    private String createdBy;
    private String modifiedBy;
    private String status;

}
