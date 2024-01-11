package com.senior.hr;

import com.senior.hr.model.*;
import com.senior.hr.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
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

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Role applicantRole = new Role();
        applicantRole.setRoleName("Applicant");
        roleRepository.save(applicantRole);
        Role employeeRole = new Role();
        employeeRole.setRoleName("Employee");
        roleRepository.save(employeeRole);
        Role managerRole = new Role();
        managerRole.setRoleName("Manager");
        roleRepository.save(managerRole);
        Applicant applicant = new Applicant();
        applicant.setUsername("abd1");
        applicant.setPassword(passwordEncoder.encode("1234"));
        applicant.setRole(applicantRole);
        applicant.setDateOfBirth(new Date(100, 12, 5));
        applicantRepository.save(applicant);
        Position position = new Position();
        position.setPositionName("juniorDev");
        positionRepository.save(position);
        Benefit benefit = new Benefit();
        benefit.setName("Transportation");
        benefit.setCutPercentage(2.3);
        Benefit benefit1 = new Benefit();
        benefit1.setName("Health insurance");
        benefit1.setCutPercentage(3.33);
        benefitRepository.saveAll(List.of(benefit, benefit1));
        Manager manager = new Manager();
        manager.setUsername("abdm");
        manager.setPassword(passwordEncoder.encode("1234"));
        manager.setRole(managerRole);
        manager.setBenefits(List.of(benefit, benefit1));
        managerRepository.save(manager);
        WarningType warningType = new WarningType();
        warningType.setName("Late");
        warningType.setMark(1);
        warningTypeRepository.save(warningType);
        Employee employee = new Employee();
        employee.setUsername("abd2");
        employee.setPassword(passwordEncoder.encode("1234"));
        employee.setRole(employeeRole);
        employee.setPosition(position);
        employee.setManager(manager);
        employee.setBenefits(List.of(benefit, benefit1));
        employeeRepository.save(employee);
        Warning warning = new Warning();
        warning.setWarningType(warningType);
        warning.setDescription("dsf");
        warning.setIssuerManager(manager);
        warning.setEmployee(employee);
        warningRepository.save(warning);
        PreviousProject previousProject = new PreviousProject();
        previousProject.setCompanyName("name");
        previousProject.setName("gg");
        previousProject.setDescription("df");
        previousProjectRepository.save(previousProject);
        PreviousProject previousProject1 = new PreviousProject();
        previousProject1.setCompanyName("name");
        previousProject1.setName("gg");
        previousProject1.setDescription("df");
        previousProjectRepository.save(previousProject1);
        PreviousProject previousProject2 = new PreviousProject();
        previousProject2.setCompanyName("name");
        previousProject2.setName("gg");
        previousProject2.setDescription("df");
        previousProjectRepository.save(previousProject2);
        Vacancy vacancy = new Vacancy();
        vacancy.setJobSalary(233.3);
        vacancy.setYearsOfExperience(3);
        vacancy.setJobDescription("fvsdufusaydfuyadsgfuysdgfuyadsgfuysdgdfyusdgfyusgfyugsf");
        vacancy.setJobTitle(position);
        vacancyRepository.save(vacancy);
        Vacancy vacancy1 = new Vacancy();
        vacancy1.setJobSalary(233.3);
        vacancy1.setYearsOfExperience(5);
        vacancy1.setJobDescription("hiiiiiiiiiiiiiiiiiii");
        vacancy1.setJobTitle(position);
        vacancyRepository.save(vacancy1);
        Application application = new Application();
        application.setPreviousProjects(List.of(previousProject, previousProject1));
        application.setApplicationDate(Date.valueOf(LocalDate.now()));
        application.setMotivationLetter("hi asjbfjasbdiuf hsafdjonbasdjfhisadf hfiashdfoiuashdfiohsad hsoifhasiodhfoisahdfoi hsaoifhsaio hoisahfio ashfiohsaoifh saiofhisaohf iosahfoi sahdifoh asiodfhoias hfioash iofhdfiohsaiodfhsaiodfh aisohf oiasdhfiosadhf iohsdf ioashfioh asoifhosaifh oiashf iosadhfioash fiohasfio hsaoif hsioafh iosahfioash fiohas iofhasio fhiosafhsio hasio fhasoifh iosafhoifh ");
        application.setApplicant(applicant);
        application.setVacancy(vacancy);
        applicationRepository.save(application);
        Application application1 = new Application();
        application1.setPreviousProjects(List.of(previousProject2));
        application1.setApplicationDate(Date.valueOf(LocalDate.now()));
        application1.setMotivationLetter("hi asjbfjasbdiuf hsafdjonbasdjfhisadf hfiashdfoiuashdfiohsad hsoifhasiodhfoisahdfoi hsaoifhsaio hoisahfio ashfiohsaoifh saiofhisaohf iosahfoi sahdifoh asiodfhoias hfioash iofhdfiohsaiodfhsaiodfh aisohf oiasdhfiosadhf iohsdf ioashfioh asoifhosaifh oiashf iosadhfioash fiohasfio hsaoif hsioafh iosahfioash fiohas iofhasio fhiosafhsio hasio fhasoifh iosafhoifh ");
        application1.setApplicant(applicant);
        application1.setVacancy(vacancy);
        //application1.setQualifiedForInterview(true);
        Vacation vacation = new Vacation();
        vacation.setNumberOfDays(2);
        vacation.setStartDate(Date.valueOf("2024-01-07"));
        vacation.setEndDate(Date.valueOf("2024-01-09"));
        vacation.setPayed(true);
        vacation.setEmployee(employee);
        vacation.setApproved(false);
        vacationRepository.save(vacation);
        Vacation vacation1 = new Vacation();
        vacation1.setNumberOfDays(2);
        vacation1.setStartDate(Date.valueOf("2024-01-07"));
        vacation1.setEndDate(Date.valueOf("2024-01-09"));
        vacation1.setPayed(true);
        vacation1.setEmployee(employee);
        vacation1.setApproved(true);
        vacationRepository.save(vacation1);
        applicationRepository.save(application1);
    }
}