package com.senior.hr.service;

import com.senior.hr.DTO.ApplicationDTO;
import com.senior.hr.DTO.QualifyApplicationRequestDTO;
import com.senior.hr.DTO.QualifyApplicationResponseDTO;

import java.util.List;

public interface ApplicationService {
    void addApplication(ApplicationDTO applicationDTO);

    void deleteApplication(Long applicationID);

    List<ApplicationDTO> findAllApplicationsByVacancy(Long vacancyID);

    List<ApplicationDTO> findAllApplicationsByApplicant(String applicantUsername);

    List<ApplicationDTO> findAllApplications();

    QualifyApplicationResponseDTO qualifyApplication(QualifyApplicationRequestDTO qualifyApplicationRequestDTO);

    List<ApplicationDTO> findAllInterviews();

}
