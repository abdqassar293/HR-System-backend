package com.senior.hr.service;

import com.senior.hr.DTO.*;

import java.util.List;

public interface ApplicationService {
    void addApplication(ApplicationDTO applicationDTO);

    void deleteApplication(Long applicationID);

    List<ApplicationDTO> findAllApplicationsByVacancy(Long vacancyID);

    List<ApplicationDTO> findAllApplicationsByApplicant(String applicantUsername);

    List<ApplicationDTO> findAllApplications();

    QualifyApplicationResponseDTO qualifyApplication(QualifyApplicationRequestDTO qualifyApplicationRequestDTO);

    List<InterviewResponseDTO> findAllInterviews();

    List<InterviewResponseDTO> findTodaysInterviews();

    void HireApplicant(HireRequestDTO hireRequestDTO);

}
