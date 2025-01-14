package org.llamagas.servicelayer.repository;

import org.llamagas.servicelayer.storeprocedures.GoalReportDTO;

import java.util.List;

public interface GoalReportRepository {

    List<GoalReportDTO> getGoalReport(String username, String status);
}
