package com.senior.hr.DTO;

import lombok.Data;

@Data
public class SalaryCalculationResponseForOneEmployeeDTO {
    private String employeeUsername;
    private Double baseSalary;
    private Double realSalary;
}
