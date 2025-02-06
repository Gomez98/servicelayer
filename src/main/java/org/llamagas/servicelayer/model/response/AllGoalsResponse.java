package org.llamagas.servicelayer.model.response;

import lombok.Data;
import org.llamagas.servicelayer.model.domain.GoalHeader;

import java.util.List;

@Data
public class AllGoalsResponse {
    private List<GoalHeader> goalHeaders;
    private Integer totalGoals;
}
