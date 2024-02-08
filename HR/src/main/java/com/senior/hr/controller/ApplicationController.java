package com.senior.hr.controller;

import com.senior.hr.DTO.*;
import com.senior.hr.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
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
    public List<ApplicationDTO> listAllByApplicant(@RequestParam String applicantUsername) {
        return applicationService.findAllApplicationsByApplicant(applicantUsername);
    }

    @GetMapping("listAllByVacancy")
    public List<ApplicationDTO> listAllByVacancy(@RequestParam String vacancyID) {
        return applicationService.findAllApplicationsByVacancy(Long.valueOf(vacancyID));
    }

    @GetMapping("interviews")
    public List<InterviewResponseDTO> listInterviews() {
        return applicationService.findAllInterviews();
    }

    @GetMapping("interviewsToday")
    public List<InterviewResponseDTO> listTodayInterviews() {
        return applicationService.findTodaysInterviews();
    }

    @PostMapping("qualifyApplication")
    public QualifyApplicationResponseDTO qualifyApplication(@RequestBody QualifyApplicationRequestDTO qualifyApplicationRequestDTO) {
        return applicationService.qualifyApplication(qualifyApplicationRequestDTO);
    }

    @PostMapping("hire")
    public void hire(@RequestBody HireRequestDTO hireRequestDTO) {
        applicationService.HireApplicant(hireRequestDTO);
    }
}
