package com.senior.hr.controller;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.model.Benefit;
import com.senior.hr.repository.BenefitRepository;
import com.senior.hr.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    //private final BenefitRepository benefitRepository;

    @GetMapping("findByUsername")
    public EmployeeDTO findApplicantById(@RequestParam String username) {
        return employeeService.findEmployeeByUsername(username);
    }
    @GetMapping("listAll")
    public List<EmployeeDTO> listAllApplicants() {
        return employeeService.findAllEmployee();
    }

    @DeleteMapping("deleteEmployee")
    public void deleteEmployee(@RequestParam String username) {
        employeeService.deleteEmployeeByUsername(username);
    }


}
