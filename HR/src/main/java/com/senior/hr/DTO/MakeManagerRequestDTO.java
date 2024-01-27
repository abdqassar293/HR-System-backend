package com.senior.hr.DTO;

import lombok.Data;

@Data
public class MakeManagerRequestDTO {
    private String employeeUsername;
    private Double newSalary;
    private String newContractNumber;
}
