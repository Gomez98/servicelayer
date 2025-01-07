package org.llamagas.servicelayer.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "GoalDetail", schema = "dbo")
@Data
public class GoalDetail {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "headerId", nullable = false)
    private GoalHeader goalHeader;

    private String content;
}

