package org.llamagas.servicelayer.storeprocedures;

import lombok.Data;

@Data
public class GoalReportDTO {
    private String headerId;
    private String headerDescription;
    private String createdBy;
    private String modifiedBy;
    private String status;
    private String detailId;
    private String detailContent;
}
