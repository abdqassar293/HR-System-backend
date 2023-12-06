package com.senior.hr.controller;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.mapper.ApplicantMapper;
import com.senior.hr.model.Applicant;
import com.senior.hr.repository.ApplicantRepository;
import com.senior.hr.service.ApplicantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MyController {
    private final ApplicantRepository applicantService;
    private final ApplicantMapper applicantMapper;
    @GetMapping("/testing")
    public ApplicantDTO testing() {
        return applicantMapper.applicantToApplicantDTO(applicantService.findById(1L).get());
    }
}
