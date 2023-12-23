package com.senior.hr.controller;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.service.ApplicantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@RequestMapping("/api/applicant")
@AllArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;

    @PostMapping("addNewApplicant")
    public void addNewApplicant(@RequestBody ApplicantDTO applicantDTO) {
        applicantService.addApplicant(applicantDTO);
    }

    @DeleteMapping("deleteApplicant")
    public void deleteApplicant(@RequestParam String applicantID) {
        applicantService.deleteApplicantById(Long.valueOf(applicantID));
    }

    @GetMapping("listAll")
    public List<ApplicantDTO> listAllApplicants() {
        return applicantService.findAllApplicant();
    }
}
