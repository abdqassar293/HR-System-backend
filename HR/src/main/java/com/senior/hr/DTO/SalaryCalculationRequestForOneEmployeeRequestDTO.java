package com.senior.hr.DTO;

import lombok.Data;

@Data
public class SalaryCalculationRequestForOneEmployeeRequestDTO {
    private String employeeUsername;
    private Integer month;
    private Integer year;
}
