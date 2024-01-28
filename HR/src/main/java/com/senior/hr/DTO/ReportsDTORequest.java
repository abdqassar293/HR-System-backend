package com.senior.hr.DTO;

import lombok.Data;

@Data
public class ReportsDTORequest {
    private String reportDescription;
    private String managerUsername;
    private String employeeUsername;
}
