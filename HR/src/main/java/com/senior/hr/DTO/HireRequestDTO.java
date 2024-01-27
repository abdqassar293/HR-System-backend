package com.senior.hr.DTO;

import lombok.Data;

@Data
public class HireRequestDTO {
    private Long applicationId;
    private String managerUsername;
    private Double salary;
    private String contractNumber;

}
