package com.senior.hr.service;

import com.senior.hr.DTO.AddBenefitToEmployeeDTO;
import com.senior.hr.DTO.BenefitDTO;
import com.senior.hr.mapper.BenefitMapper;
import com.senior.hr.model.Benefit;
import com.senior.hr.model.Employee;
import com.senior.hr.repository.BenefitRepository;
import com.senior.hr.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BenefitServiceImpl implements BenefitService {
    private final BenefitRepository benefitRepository;
    private final BenefitMapper benefitMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<BenefitDTO> listAllBenefits() {
        return benefitRepository.findAll().stream().map(benefitMapper::benefitToBenefitDTO).collect(Collectors.toList());
    }

    @Override
    public BenefitDTO addNewBenefit(BenefitDTO benefitDTO) {
        Benefit benefit = benefitMapper.benefitDTOToBenefit(benefitDTO);
        return benefitMapper.benefitToBenefitDTO(benefitRepository.save(benefit));
    }

    @Override
    public void deleteBenefitById(Long benefitId) {
        Benefit benefit = benefitRepository.findById(benefitId).orElseThrow();
        List<Employee> employees = benefit.getEmployees();
        employees.forEach(employee -> employee.getBenefits().remove(benefit));
        benefitRepository.deleteById(benefitId);
    }

    @Override
    public void addBenefitToEmployee(AddBenefitToEmployeeDTO addBenefitToEmployeeDTO) {
        Employee employee = employeeRepository.findByUsername(addBenefitToEmployeeDTO.getUsername()).orElseThrow();
        Benefit benefit = benefitRepository.findById(addBenefitToEmployeeDTO.getBenefitId()).orElseThrow();
        List<Benefit> benefits = employee.getBenefits();
        benefits.add(benefit);
        employee.setBenefits(benefits);
        employeeRepository.save(employee);
    }
}
