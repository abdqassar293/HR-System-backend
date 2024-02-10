package com.senior.hr;

import com.senior.hr.model.*;
import com.senior.hr.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableAsync
public class HrApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class, args);
    }

}

@Component
@AllArgsConstructor
class BootStrap implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final ApplicantRepository applicantRepository;
    private final EmployeeRepository employeeRepository;
    private final VacancyRepository vacancyRepository;
    private final PasswordEncoder passwordEncoder;
    private final PreviousProjectRepository previousProjectRepository;
    private final ApplicationRepository applicationRepository;
    private final PositionRepository positionRepository;
    private final ManagerRepository managerRepository;
    private final BenefitRepository benefitRepository;
    private final WarningTypeRepository warningTypeRepository;
    private final WarningRepository warningRepository;
    private final VacationRepository vacationRepository;
    private final UserEntityRepository userEntityRepository;
    private final ReportsRepository reportsRepository;
    private final ScreeningResultsRepository screeningResultsRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Role hrRole = new Role();
        hrRole.setRoleName("HR");
        roleRepository.save(hrRole);
        Role applicantRole = new Role();
        applicantRole.setRoleName("APP");
        roleRepository.save(applicantRole);
        Role employeeRole = new Role();
        employeeRole.setRoleName("EMP");
        roleRepository.save(employeeRole);
        Role managerRole = new Role();
        managerRole.setRoleName("MG");
        roleRepository.save(managerRole);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("hr1234");
        userEntity.setPassword(passwordEncoder.encode("1234"));
        userEntity.setRole(hrRole);
        userEntityRepository.save(userEntity);

        Applicant applicant = new Applicant();
        applicant.setUsername("app1");
        applicant.setPassword(passwordEncoder.encode("1234"));
        applicant.setRole(applicantRole);
        applicant.setDateOfBirth(new Date(100, 12, 5));
        applicant.setEmail("abd.kassar10@outlook.com");
        applicant.setFatherName("father");
        applicant.setFirstName("Thomas");
        applicant.setLastName("Tuchel");
        applicant.setMotherName("motherName");
        applicant.setDegree("computer Science");
        applicant.setEmail("abd.kassar10@outlook.com");
        applicant.setPlaceOfBirth("Syria");
        applicant.setResidence("damascus midan alkornish building number 51");
        applicant.setNumber("0932323894");
        applicant.setSsn("90327470");
        applicantRepository.save(applicant);

        Applicant applicant1 = new Applicant();
        applicant1.setUsername("app2");
        applicant1.setPassword(passwordEncoder.encode("1234"));
        applicant1.setRole(applicantRole);
        applicant1.setDateOfBirth(new Date(100, 12, 5));
        applicant1.setEmail("abd.kassar10@outlook.com");
        applicant1.setFatherName("fatherName");
        applicant1.setFirstName("Didier");
        applicant1.setLastName("Drogba");
        applicant1.setMotherName("motherName");
        applicant1.setDegree("computer Science");
        applicant1.setEmail("abd.kassar10@outlook.com");
        applicant1.setPlaceOfBirth("Syria");
        applicant1.setResidence("damascus Malki Addnan Almalki square building number 3");
        applicant1.setNumber("0932323347");
        applicant1.setSsn("90327470");
        applicantRepository.save(applicant1);

        Position position = new Position();
        position.setPositionName("juniorDev");
        positionRepository.save(position);

        Position position1 = new Position();
        position1.setPositionName("SeniorDev");
        positionRepository.save(position1);

        Benefit benefit = new Benefit();
        benefit.setName("Transportation");
        benefit.setCutPercentage(0.23);

        Benefit benefit1 = new Benefit();
        benefit1.setName("Health insurance");
        benefit1.setCutPercentage(0.10);
        benefitRepository.saveAll(List.of(benefit, benefit1));

        Manager manager = new Manager();
        manager.setUsername("mg1");
        manager.setPassword(passwordEncoder.encode("1234"));
        manager.setRole(managerRole);
        manager.setBenefits(Set.of(benefit, benefit1));
        manager.setFirstName("Eden");
        manager.setMotherName("MotherName");
        manager.setLastName("Hazard");
        manager.setPlaceOfBirth("Belgium");
        manager.setDegree("PHD");
        manager.setFatherName("FatherName");
        manager.setSalary(2332.3);
        manager.setSsn("23432432423410");
        manager.setDateOfBirth(new Date(100, 12, 5));
        manager.setNumber("0933234234");
        manager.setResidence("Damascus abu remaneh Building number 5");
        manager.setContractNumber("B85543");
        manager.setEmail("abd.kassar10@outlook.com");
        managerRepository.save(manager);

        Manager manager1 = new Manager();
        manager1.setUsername("mg2");
        manager1.setPassword(passwordEncoder.encode("1234"));
        manager1.setRole(managerRole);
        manager1.setLastName("bono");
        manager1.setFirstName("yassin");
        manager1.setBenefits(Set.of(benefit, benefit1));
        manager1.setMotherName("MotherName");
        manager1.setPlaceOfBirth("Morocco");
        manager1.setDegree("phd");
        manager1.setFatherName("FatherName");
        manager1.setSalary(2332.3);
        manager1.setSsn("23432432423410");
        manager1.setDateOfBirth(new Date(100, 12, 5));
        manager1.setNumber("0933234234");
        manager1.setResidence("midan Kornish building number 55");
        manager1.setContractNumber("B85523");
        manager1.setEmail("abd.kassar10@outlook.com");
        managerRepository.save(manager1);

        WarningType warningType = new WarningType();
        warningType.setName("Late");
        warningType.setMark(1);
        warningTypeRepository.save(warningType);

        WarningType warningType1 = new WarningType();
        warningType1.setName("absentee");
        warningType1.setMark(1);
        warningTypeRepository.save(warningType1);

        Employee employee = new Employee();
        employee.setUsername("emp1");
        employee.setPassword(passwordEncoder.encode("1234"));
        employee.setContractNumber("A34234");
        employee.setRole(employeeRole);
        employee.setPlaceOfBirth("Syria");
        employee.setDegree("PHD");
        employee.setFirstName("Frank");
        employee.setLastName("Lampard");
        employee.setFatherName("FatherName");
        employee.setMotherName("MotherName");
        employee.setSalary(1332.0);
        employee.setSsn("23432432423410");
        employee.setDateOfBirth(new Date(100, 12, 5));
        employee.setNumber("0933234234");
        employee.setResidence("London Fulham street building no.34 ");
        employee.setPosition(position);
        employee.setManager(manager);
        employee.setBenefits(Set.of(benefit, benefit1));
        employeeRepository.save(employee);

        Employee employee1 = new Employee();
        employee1.setUsername("emp2");
        employee1.setPassword(passwordEncoder.encode("1234"));
        employee1.setContractNumber("A32433");
        employee1.setRole(employeeRole);
        employee1.setPlaceOfBirth("Syria");
        employee1.setDegree("Computer science");
        employee1.setFirstName("John");
        employee1.setLastName("Terry");
        employee1.setFatherName("FatherName");
        employee1.setMotherName("MotherName");
        employee1.setSalary(2332.3);
        employee1.setSsn("23432432423410");
        employee1.setDateOfBirth(new Date(100, 12, 5));
        employee1.setNumber("0933234234");
        employee1.setResidence("London Fullham street building no.35");
        employee1.setPosition(position1);
        employee1.setManager(manager1);
        employee1.setBenefits(Set.of(benefit, benefit1));
        employeeRepository.save(employee1);

        Warning warning = new Warning();
        warning.setWarningType(warningType);
        warning.setDescription("he is late as usual");
        warning.setIssuerManager(manager);
        warning.setEmployee(employee);
        warningRepository.save(warning);

        PreviousProject previousProject = new PreviousProject();
        previousProject.setCompanyName("CompanyName");
        previousProject.setName("ProjectName");
        previousProject.setDescription("we used Spring boot Framework");
        previousProjectRepository.save(previousProject);

        PreviousProject previousProject1 = new PreviousProject();
        previousProject1.setCompanyName("CompanyName");
        previousProject1.setName("ProjectName1");
        previousProject1.setDescription("we used React Native");
        previousProjectRepository.save(previousProject1);

        PreviousProject previousProject2 = new PreviousProject();
        previousProject2.setCompanyName("CompanyName");
        previousProject2.setName("ProjectName2");
        previousProject2.setDescription("we used Bash programming");
        previousProjectRepository.save(previousProject2);

        Vacancy vacancy = new Vacancy();
        vacancy.setJobSalary(2333.3);
        vacancy.setYearsOfExperience(5);
        vacancy.setJobDescription("Full time SeniorDev good at Backend programming");
        vacancy.setJobTitle(position1);
        vacancy.setJobType(JobTypeEnum.FullTime);
        vacancyRepository.save(vacancy);

        Vacancy vacancy1 = new Vacancy();
        vacancy1.setJobSalary(1000.0);
        vacancy1.setYearsOfExperience(1);
        vacancy1.setJobDescription("Internship JuniorDev good with python programming language");
        vacancy1.setJobTitle(position);
        vacancy1.setJobType(JobTypeEnum.InternShip);
        vacancyRepository.save(vacancy1);

        ScreeningResults screeningResults = new ScreeningResults();
        screeningResults.setSoftwareDeveloper(0.77);
        screeningResults.setFrontEnd(0.77);
        screeningResults.setNetworkAdmin(0.77);
        screeningResults.setWebDeveloper(0.77);
        screeningResults.setProjectManager(0.77);
        screeningResults.setDatabaseAdmin(0.77);
        screeningResults.setSecurityAnalyst(0.77);
        screeningResults.setSystemAdmin(0.77);
        screeningResults.setPythonDeveloper(0.77);
        screeningResults.setJavaDeveloper(0.77);
        screeningResultsRepository.save(screeningResults);

        ScreeningResults screeningResults1 = new ScreeningResults();
        screeningResults1.setSoftwareDeveloper(0.77);
        screeningResults1.setFrontEnd(0.77);
        screeningResults1.setNetworkAdmin(0.77);
        screeningResults1.setWebDeveloper(0.77);
        screeningResults1.setProjectManager(0.77);
        screeningResults1.setDatabaseAdmin(0.77);
        screeningResults1.setSecurityAnalyst(0.77);
        screeningResults1.setSystemAdmin(0.77);
        screeningResults1.setPythonDeveloper(0.77);
        screeningResults1.setJavaDeveloper(0.77);
        screeningResultsRepository.save(screeningResults1);

        Application application = new Application();
        application.setPreviousProjects(List.of(previousProject1, previousProject2));
        application.setApplicationDate(Date.valueOf(LocalDate.now()));
        application.setMotivationLetter("jffffffffffffffffffffffdshgfdsjighdsfjghdsfiopghpdsfighidsfhgoidsfhgoidfsgoifdshgiopdsfh");
        application.setApplicant(applicant);
        application.setVacancy(vacancy1);
        application.setQualifiedForInterview(false);
        application.setEnglishLevel("B1");
        application.setProgrammingLanguage("python");
        application.setScreeningResults(screeningResults);
        applicationRepository.save(application);

        Application application1 = new Application();
        application1.setPreviousProjects(List.of(previousProject));
        application1.setApplicationDate(Date.valueOf(LocalDate.now()));
        application1.setMotivationLetter("ghhhhhhhhghghtuguhtuhguthjdfpgbdfsgidsfhgidhsfgpdfgihdfgiodhfgiopdfhgoidhsfgopidsfgpoidhfgiodshfgiohdfsioghdopsfhgoidfshgoidhfsgiohdsfighdoisfhgoidsphfgpoidhfsgidsfghpdfoghpdofghdosfpghodifhgdfogpdfshgpdosfghidfgihdfpsghdfhigopdsifghoidfhgoidhfghidfghoifdpshgoipdfhgpoidgi");
        application1.setApplicant(applicant1);
        application1.setVacancy(vacancy);
        application1.setQualifiedForInterview(false);
        application1.setEnglishLevel("A1");
        application1.setProgrammingLanguage("java");
        application1.setScreeningResults(screeningResults1);
        applicationRepository.save(application1);

        Application application2 = new Application();
        application2.setPreviousProjects(List.of());
        application2.setApplicationDate(Date.valueOf(LocalDate.now()));
        application2.setMotivationLetter("ghhhhhhhhghghtuguhtuhguthjdfpgbdfsgidsfhgidhsfgpdfgihdfgiodhfgiopdfhgoidhsfgopidsfgpoidhfgiodshfgiohdfsioghdopsfhgoidfshgoidhfsgiohdsfighdoisfhgoidsphfgpoidhfsgidsfghpdfoghpdofghdosfpghodifhgdfogpdfshgpdosfghidfgihdfpsghdfhigopdsifghoidfhgoidhfghidfghoifdpshgoipdfhgpoidgi");
        application2.setApplicant(applicant1);
        application2.setVacancy(vacancy);
        application2.setQualifiedForInterview(true);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        application2.setInterviewDate(LocalDateTime.parse("2024-01-25 12:00", dtf));
        application2.setEnglishLevel("A1");
        application2.setProgrammingLanguage("java");
        applicationRepository.save(application2);

        Vacation vacation = new Vacation();
        vacation.setNumberOfDays(2);
        vacation.setStartDate(LocalDate.of(2024, 2, 17));
        vacation.setEndDate(LocalDate.of(2024, 2, 20));
        vacation.setPayed(true);
        vacation.setEmployee(employee);
        vacation.setApproved(false);
        vacation.setStartYearMonth(YearMonth.from(vacation.getStartDate()).toString());
        vacation.setEndYearMonth(YearMonth.from(vacation.getEndDate()).toString());
        vacationRepository.save(vacation);

        Vacation vacation1 = new Vacation();
        vacation1.setNumberOfDays(2);
        vacation1.setStartDate(LocalDate.of(2023, 11, 25));
        vacation1.setEndDate(LocalDate.of(2023, 12, 4));
        vacation1.setPayed(true);
        vacation1.setEmployee(employee);
        vacation1.setApproved(true);
        vacation1.setStartYearMonth(YearMonth.from(vacation1.getStartDate()).toString());
        vacation1.setEndYearMonth(YearMonth.from(vacation1.getEndDate()).toString());
        vacationRepository.save(vacation1);

        Report report = new Report();
        report.setReport_result("Core Player(Moderate performance, Moderate potential)");
        report.setRating(4);
        report.setIssuer(manager1);
        report.setEmployee(employee);
        report.setDateIssued(Date.valueOf(LocalDate.now()));
        report.setReportDescription("he is good at team work and working with other colleagues");
        reportsRepository.save(report);
    }
}