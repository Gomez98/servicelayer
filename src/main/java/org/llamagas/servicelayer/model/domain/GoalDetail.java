package org.llamagas.servicelayer.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GoalDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @OneToOne
    @JoinColumn(name = "headerId", nullable = false)
    private GoalHeader goalHeader;

    @Column(columnDefinition = "TEXT")
    private String content;
}

