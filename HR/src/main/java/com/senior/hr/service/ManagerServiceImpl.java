package com.senior.hr.service;

import com.senior.hr.DTO.*;
import com.senior.hr.mapper.EmployeeMapper;
import com.senior.hr.mapper.ManagerMapper;
import com.senior.hr.model.*;
import com.senior.hr.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {
    private final ReportsRepository reportsRepository;
    private final ManagerRepository managerRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final EmployeeMapper employeeMapper;
    private final ManagerMapper managerMapper;
    private final VacationRepository vacationRepository;
    private final WarningRepository warningRepository;
    private final BenefitRepository benefitRepository;

    @Override
    public List<ManagerResponseDTO> listAllManagers() {
        return managerRepository.findAll().stream().map(manager -> {
            ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO();
            managerResponseDTO.setManagerFirstName(manager.getFirstName());
            managerResponseDTO.setManagerLastName(manager.getLastName());
            managerResponseDTO.setMangerUsername(manager.getUsername());
            return managerResponseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ManagerDTO> listManagers() {
        return managerRepository.findAll().stream().map(managerMapper::managerToManagerDTO).toList();
    }

    @Transactional
    @Override
    public void deleteManagerByUsername(String username) {
        managerRepository.deleteByUsername(username);
    }

    @Transactional
    @Override
    public ManagerDTO makaManager(MakeManagerRequestDTO makeManagerRequestDTO) {
        //Todo add exception handling
        Employee employee = employeeRepository.findByUsername(makeManagerRequestDTO.getEmployeeUsername()).orElseThrow();
        vacationRepository.deleteByEmployee(employee);
        warningRepository.deleteByEmployee(employee);
        reportsRepository.deleteByEmployee(employee);
        employeeRepository.delete(employee);
        Manager manager = new Manager();
        manager.setContractNumber(makeManagerRequestDTO.getNewContractNumber());
        manager.setUsername(employee.getUsername());
        manager.setPassword(employee.getPassword());
        manager.setFirstName(employee.getFirstName());
        manager.setLastName(employee.getLastName());
        manager.setSsn(employee.getSsn());
        manager.setNumber(employee.getNumber());
        manager.setResidence(employee.getResidence());
        manager.setDegree(employee.getDegree());
        manager.setMotherName(employee.getMotherName());
        manager.setFatherName(employee.getFatherName());
        manager.setRole(roleRepository.findRoleByRoleName("Manager").orElseThrow());
        manager.setPlaceOfBirth(employee.getPlaceOfBirth());
        manager.setDateOfBirth(employee.getDateOfBirth());
        List<Benefit> benefits = benefitRepository.findAll();
        manager.setSalary(makeManagerRequestDTO.getNewSalary());
        manager.setBenefits(List.of(benefits.get(0), benefits.get(1)));
        Manager savedManager = managerRepository.save(manager);
        return managerMapper.managerToManagerDTO(savedManager);
    }

    @Override
    public void addEmployeeToManager(AddEmployeeToManagerRequest addEmployeeToManagerRequest) {
        //Todo add exception handling
        Employee employee = employeeRepository.findByUsername(addEmployeeToManagerRequest.getEmployeeUsername()).orElseThrow();
        Manager manager = managerRepository.findByUsername(addEmployeeToManagerRequest.getManagerUsername()).orElseThrow();
        employee.setManager(manager);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDTO> listAllManagerEmployees(String managerUsername) {
        //Todo add exception handling
        Manager manager = managerRepository.findByUsername(managerUsername).orElseThrow();
        return manager.getEmployees().stream().map(employeeMapper::employeeToEmployeeDTO).collect(Collectors.toList());
    }
}
