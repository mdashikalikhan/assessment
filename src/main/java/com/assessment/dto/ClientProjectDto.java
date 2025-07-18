package com.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientProjectDto {
    private Integer clientProjectId;
    private String projectName;
    private String startDate;
    private String expectedEndDate;
    private String leadByEmpId;
    private String completedDate;
    private String contactPerson;
    private String contactPersonContactNo;
    private String totalEmpWorking;
    private String projectCost;
    private String projectDetails;
    private String contactPersonEmailId;
    private String clientId;
    private String companyName;
}
