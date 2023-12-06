package com.senior.hr.service;

import com.senior.hr.DTO.ApplicantDTO;

public interface ApplicantService {
    void addApplicant(ApplicantDTO applicantDTO);

    void deleteApplicantById(Long id);
}
