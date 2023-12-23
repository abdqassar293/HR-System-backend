package com.senior.hr.service;

import com.senior.hr.DTO.ApplicationDTO;
import com.senior.hr.mapper.ApplicationMapper;
import com.senior.hr.model.Applicant;
import com.senior.hr.model.Application;
import com.senior.hr.model.Vacancy;
import com.senior.hr.repository.ApplicantRepository;
import com.senior.hr.repository.ApplicationRepository;
import com.senior.hr.repository.PreviousProjectRepository;
import com.senior.hr.repository.VacancyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;
    private final VacancyRepository vacancyRepository;
    private final ApplicantRepository applicantRepository;
    private final PreviousProjectRepository previousProjectRepository;

    @Override
    @Transactional
    public void addApplication(ApplicationDTO applicationDTO) {
        Application application = applicationMapper.applicationDTOToApplication(applicationDTO);
        //TODO Exception handling
        Applicant applicant = applicantRepository.findById(applicationDTO.getApplicant().getId()).orElseThrow();
        Vacancy vacancy = vacancyRepository.findById(applicationDTO.getVacancy().getId()).orElseThrow();
        application.setApplicant(applicant);
        application.setVacancy(vacancy);
        application.setApplicationDate(Date.valueOf(LocalDate.now()));
        previousProjectRepository.saveAll(application.getPreviousProjects());
        applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long applicationID) {
        applicationRepository.deleteById(applicationID);
    }

    @Override
    @Transactional
    public List<ApplicationDTO> findAllApplicationsByVacancy(Long vacancyID) {
        //TODO exception handling
        Vacancy vacancy = vacancyRepository.findById(vacancyID).orElseThrow();
        return applicationRepository.findAllByVacancy(vacancy).stream().map(applicationMapper::applicationToApplicationDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ApplicationDTO> findAllApplicationsByApplicant(Long applicantID) {
        //TODO exception Handling
        Applicant applicant = applicantRepository.findById(applicantID).orElseThrow();
        return applicationRepository.findAllByApplicant(applicant).stream().map(applicationMapper::applicationToApplicationDTO).collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> findAllApplications() {
        return applicationRepository.findAll().stream().map(applicationMapper::applicationToApplicationDTO).collect(Collectors.toList());
    }
}
