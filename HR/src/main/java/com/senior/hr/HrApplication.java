package com.senior.hr;

import com.senior.hr.model.Applicant;
import com.senior.hr.model.Employee;
import com.senior.hr.model.Role;
import com.senior.hr.repository.ApplicantRepository;
import com.senior.hr.repository.EmployeeRepository;
import com.senior.hr.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

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
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        Role applicantRole = new Role();
        applicantRole.setRoleName("Applicant");
        roleRepository.save(applicantRole);
        Role employeeRole = new Role();
        employeeRole.setRoleName("employee");
        roleRepository.save(employeeRole);
        Applicant applicant = new Applicant();
        applicant.setUsername("abd1");
        applicant.setPassword(passwordEncoder.encode("1234"));
        applicant.setRole(applicantRole);
        applicant.setDateOfBirth(new Date(100, 12, 5));
        applicantRepository.save(applicant);
        Employee employee = new Employee();
        employee.setUsername("abd2");
        employee.setPassword(passwordEncoder.encode("1234"));
        employee.setRole(employeeRole);
        employee.setJobTitle("programmer");
        employeeRepository.save(employee);
    }
}