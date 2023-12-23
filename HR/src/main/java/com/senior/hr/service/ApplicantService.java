package com.senior.hr.service;

import com.senior.hr.DTO.ApplicantDTO;

import java.util.List;

public interface ApplicantService {
    void addApplicant(ApplicantDTO applicantDTO);

    void deleteApplicantById(Long id);

    List<ApplicantDTO> findAllApplicant();
}
