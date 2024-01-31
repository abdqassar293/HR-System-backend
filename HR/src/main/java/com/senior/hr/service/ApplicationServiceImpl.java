package com.senior.hr.service;

import com.senior.hr.DTO.*;
import com.senior.hr.mapper.ApplicationMapper;
import com.senior.hr.model.*;
import com.senior.hr.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private final BenefitRepository benefitRepository;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ManagerRepository managerRepository;
    private final RoleRepository roleRepository;
    private final JavaMailSender mailSender;
    @Override
    @Transactional
    public void addApplication(ApplicationDTO applicationDTO) {
        Application application = applicationMapper.applicationDTOToApplication(applicationDTO);
        //TODO Exception handling
        Applicant applicant = applicantRepository.findByUsername(applicationDTO.getApplicant().getUsername()).orElseThrow();
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
    public List<ApplicationDTO> findAllApplicationsByApplicant(String applicantUsername) {
        //TODO exception Handling
        Applicant applicant = applicantRepository.findByUsername(applicantUsername).orElseThrow();
        return applicationRepository.findAllByApplicant(applicant).stream().map(applicationMapper::applicationToApplicationDTO).collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDTO> findAllApplications() {
        return applicationRepository.findAll().stream().map(applicationMapper::applicationToApplicationDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QualifyApplicationResponseDTO qualifyApplication(QualifyApplicationRequestDTO qualifyApplicationRequestDTO) {
        //Todo exception Handling
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Application application = applicationRepository.findById(Long.valueOf(qualifyApplicationRequestDTO.getApplicationId())).orElseThrow();
        application.setInterviewDate(LocalDateTime.parse(qualifyApplicationRequestDTO.getInterviewDate(), dtf));
        application.setQualifiedForInterview(true);
        application.setId(Long.valueOf(qualifyApplicationRequestDTO.getApplicationId()));
        application = applicationRepository.save(application);
        QualifyApplicationResponseDTO qualifyApplicationResponseDTO = new QualifyApplicationResponseDTO();
        qualifyApplicationResponseDTO.setInterviewDate(application.getInterviewDate().format(dtf));
        qualifyApplicationResponseDTO.setMessage("Date reserved");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(application.getApplicant().getEmail());
        message.setSubject("you are qualified for an interview");
        message.setText("you are qualified for an Interview at our company for your application for " + application.getVacancy().getApplications() + " job." + '\n' + "your interview is at " + application.getApplicationDate().toLocalDate().getDayOfWeek() + " " + qualifyApplicationResponseDTO.getInterviewDate() + ".");
        mailSender.send(message);
        return qualifyApplicationResponseDTO;
    }

    @Override
    @Transactional
    public List<InterviewResponseDTO> findAllInterviews() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return applicationRepository.findAllByQualifiedForInterviewIsTrue().stream().map(application -> {
            InterviewResponseDTO interviewResponseDTO = new InterviewResponseDTO();
            interviewResponseDTO.setDate(application.getInterviewDate().format(dtf));
            interviewResponseDTO.setApplicantUsername(application.getApplicant().getUsername());
            interviewResponseDTO.setVacancyName(application.getVacancy().getJobTitle().getPositionName());
            return interviewResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<InterviewResponseDTO> findTodaysInterviews() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        //LocalDate now=LocalDate.now();
        LocalDate now = LocalDate.of(2024, 1, 25);
        List<InterviewResponseDTO> list = new ArrayList<>();
        applicationRepository.findAllByQualifiedForInterviewIsTrue().forEach(application -> {
            if (now.isEqual(application.getInterviewDate().toLocalDate())) {
                InterviewResponseDTO interviewResponseDTO = new InterviewResponseDTO();
                interviewResponseDTO.setDate(application.getInterviewDate().format(dtf));
                interviewResponseDTO.setApplicantUsername(application.getApplicant().getUsername());
                interviewResponseDTO.setVacancyName(application.getVacancy().getJobTitle().getPositionName());
                list.add(interviewResponseDTO);
            }
        });
        return list;
    }

    @Transactional
    @Override
    public void HireApplicant(HireRequestDTO hireRequestDTO) {
        //Todo add exception handling
        Application application = applicationRepository.findById(hireRequestDTO.getApplicationId()).orElseThrow();
        applicationRepository.deleteAllByApplicant(application.getApplicant());
        Applicant applicant = applicantRepository.findByUsername(application.getApplicant().getUsername()).orElseThrow();
        applicantRepository.deleteById(applicant.getId());
        Employee employee = new Employee();
        employee.setContractNumber(hireRequestDTO.getContractNumber());
        employee.setUsername(applicant.getUsername());
        employee.setPassword(applicant.getPassword());
        employee.setFirstName(applicant.getFirstName());
        employee.setLastName(applicant.getLastName());
        employee.setSsn(applicant.getSsn());
        employee.setNumber(applicant.getNumber());
        employee.setResidence(applicant.getResidence());
        employee.setDegree(applicant.getDegree());
        employee.setMotherName(applicant.getMotherName());
        employee.setFatherName(applicant.getFatherName());
        employee.setRole(roleRepository.findRoleByRoleName("Employee").orElseThrow());
        employee.setPlaceOfBirth(applicant.getPlaceOfBirth());
        employee.setDateOfBirth(applicant.getDateOfBirth());
        List<Benefit> benefits = benefitRepository.findAll();
        employee.setSalary(hireRequestDTO.getSalary());
        employee.setBenefits(List.of(benefits.get(0), benefits.get(1)));
        employee.setPosition(positionRepository.findByPositionName(application.getVacancy().getJobTitle().getPositionName()).orElseThrow());
        employee.setManager(managerRepository.findByUsername(hireRequestDTO.getManagerUsername()).orElseThrow());
        employeeRepository.save(employee);
    }
}
