package com.senior.hr.DTO;

import lombok.Data;

@Data
public class AddEmployeeToManagerRequest {
    private String employeeUsername;
    private String managerUsername;
}
