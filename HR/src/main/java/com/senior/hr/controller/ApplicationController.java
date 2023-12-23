package com.senior.hr.controller;

import com.senior.hr.DTO.ApplicationDTO;
import com.senior.hr.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("listAll")
    public List<ApplicationDTO> listAll() {
        return applicationService.findAllApplications();
    }

    @PostMapping("addNewApplication")
    public void addNewApplication(@RequestBody ApplicationDTO applicationDTO) {
        applicationService.addApplication(applicationDTO);
    }

    @DeleteMapping("deleteApplication")
    public void deleteApplication(@RequestParam String applicationID) {
        applicationService.deleteApplication(Long.valueOf(applicationID));
    }

    @GetMapping("listAllByApplicant")
    public List<ApplicationDTO> listAllByApplicant(@RequestParam String applicantID) {
        return applicationService.findAllApplicationsByApplicant(Long.valueOf(applicantID));
    }

    @GetMapping("listAllByVacancy")
    public List<ApplicationDTO> listAllByVacancy(@RequestParam String vacancyID) {
        return applicationService.findAllApplicationsByVacancy(Long.valueOf(vacancyID));
    }
}
