package com.senior.hr.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationDTO {
    private Long id;
    private String motivationLetter;
    private String englishLevel;
    private String programmingLanguage;
    private List<PreviousProjectDTO> previousProjects = new ArrayList<>();
    private String applicationDate;
    private Boolean qualifiedForInterview;
    private String interviewDate;
    private VacancyDTO vacancy;
    private ApplicantDTO applicant;
}
