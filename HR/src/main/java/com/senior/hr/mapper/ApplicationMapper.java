package com.senior.hr.mapper;

import com.senior.hr.DTO.ApplicationDTO;
import com.senior.hr.model.Application;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationMapper {
    private final ApplicantMapper applicantMapper;
    private final VacancyMapper vacancyMapper;
    private final PreviousProjectMapper previousProjectMapper;

    public Application applicationDTOToApplication(ApplicationDTO applicationDTO) {
        Application application = new Application();
        application.setEnglishLevel(applicationDTO.getEnglishLevel());
        application.setMotivationLetter(applicationDTO.getMotivationLetter());
        application.setProgrammingLanguage(application.getProgrammingLanguage());
        application.setApplicationDate(Date.valueOf(applicationDTO.getApplicationDate()));
        application.setInterviewDate(Date.valueOf(applicationDTO.getInterviewDate()));
        application.setQualifiedForInterview(applicationDTO.getQualifiedForInterview());
        application.setPreviousProjects(applicationDTO.getPreviousProjects().stream().map(previousProjectMapper::previousProjectDTOTOPreviousProject).collect(Collectors.toList()));
        return application;
    }

    public ApplicationDTO applicationToApplicationDTO(Application application) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setEnglishLevel(application.getEnglishLevel());
        applicationDTO.setProgrammingLanguage(application.getProgrammingLanguage());
        Date applicationDate = application.getApplicationDate();
        if (applicationDate != null) {
            applicationDTO.setApplicationDate(applicationDate.toString());
        }
        applicationDTO.setMotivationLetter(application.getMotivationLetter());
        Date interviewDate = application.getInterviewDate();
        if (interviewDate != null) {
            applicationDTO.setInterviewDate(interviewDate.toString());
        }
        applicationDTO.setQualifiedForInterview(application.getQualifiedForInterview());
        applicationDTO.setVacancy(vacancyMapper.vacancyToVacancyDTO(application.getVacancy()));
        applicationDTO.setApplicant(applicantMapper.applicantToApplicantDTO(application.getApplicant()));
        applicationDTO.setPreviousProjects(application.getPreviousProjects().stream().map(previousProjectMapper::previousProjectToPreviousProjectDTO).collect(Collectors.toList()));
        return applicationDTO;
    }

}
