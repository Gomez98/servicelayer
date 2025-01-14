package org.llamagas.servicelayer.repository.impl;

import org.llamagas.servicelayer.repository.GoalReportRepository;
import org.llamagas.servicelayer.storeprocedures.GoalReportDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GoalReportRepositoryImpl implements GoalReportRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GoalReportRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<GoalReportDTO> getGoalReport(String username, String status) {
        String sql = "EXEC sp_GetGoalReport @Username = :username, @Status = :status";

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("status", status);

        return namedParameterJdbcTemplate.query(
                sql,
                params,
                (rs, rowNum) -> {
                    GoalReportDTO dto = new GoalReportDTO();
                    dto.setHeaderId(rs.getString("HeaderId"));
                    dto.setHeaderDescription(rs.getString("HeaderDescription"));
                    dto.setCreatedBy(rs.getString("createdBy"));
                    dto.setModifiedBy(rs.getString("modifiedBy"));
                    dto.setStatus(rs.getString("status"));
                    dto.setDetailId(rs.getString("DetailId"));
                    dto.setDetailContent(rs.getString("DetailContent"));
                    return dto;
                }
        );
    }
}
