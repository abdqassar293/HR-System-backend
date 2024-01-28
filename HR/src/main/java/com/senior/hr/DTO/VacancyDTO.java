package com.senior.hr.DTO;

import com.senior.hr.model.JobTypeEnum;
import lombok.Data;

@Data
public class VacancyDTO {
    private Long id;
    private String jobTitle;
    private Integer yearsOfExperience;
    private Double jobSalary;
    private String jobDescription;
    private String jobType;

}
