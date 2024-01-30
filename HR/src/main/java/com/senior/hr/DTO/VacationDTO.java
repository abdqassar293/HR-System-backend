package com.senior.hr.DTO;

import lombok.Data;

@Data
public class VacationDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private Boolean payed;
    private Integer numberOfDays;
    private Boolean approved;
    private String employeeUsername;
}
