package com.senior.hr.DTO;

import lombok.Data;

@Data
public class WarningAddRequestDTO {
    private String warningType;
    private String employeeUsername;
    private String managerUsername;
    private String description;
}
