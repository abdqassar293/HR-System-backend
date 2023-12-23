package com.senior.hr.DTO;

import lombok.Data;

@Data
public class VacancyDTO {
    private Long id;
    private String jobTitle;
    private Double jobSalary;
    private String jobDescription;
}
