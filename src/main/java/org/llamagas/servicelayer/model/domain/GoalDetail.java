package org.llamagas.servicelayer.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GoalDetail {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "headerId", nullable = false)
    private GoalHeader goalHeader;

    @Column(columnDefinition = "TEXT")
    private String content;
}

