package com.senior.hr.service;

import com.senior.hr.DTO.ApplicationDTO;

import java.util.List;

public interface ApplicationService {
    void addApplication(ApplicationDTO applicationDTO);

    void deleteApplication(Long applicationID);

    List<ApplicationDTO> findAllApplicationsByVacancy(Long vacancyID);

    List<ApplicationDTO> findAllApplicationsByApplicant(Long applicantID);

    List<ApplicationDTO> findAllApplications();

}
