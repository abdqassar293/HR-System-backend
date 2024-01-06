package com.senior.hr.DTO;

import lombok.Data;

@Data
public class WarningDTO {
    private Long id;
    private String description;
    private WarningTypeDTO warningType;
    private String employeeUsername;
    private String issuerManager;
}
