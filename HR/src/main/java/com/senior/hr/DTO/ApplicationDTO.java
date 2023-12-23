package com.senior.hr.DTO;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationDTO {
    private Long id;
    private String motivationLetter;
    private String englishLevel;
    private String programmingLanguage;
    private List<PreviousProjectDTO> previousProjects = new ArrayList<>();
    private Date applicationDate;
    private Boolean qualifiedForInterview;
    private Date interviewDate;
    private VacancyDTO vacancy;
    private ApplicantDTO applicant;

}
