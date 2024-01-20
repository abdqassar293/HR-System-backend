package com.senior.hr.service;

import com.senior.hr.DTO.WarningAddRequestDTO;
import com.senior.hr.DTO.WarningDTO;
import com.senior.hr.DTO.WarningTypeDTO;
import com.senior.hr.mapper.WarningMapper;
import com.senior.hr.mapper.WarningTypeMapper;
import com.senior.hr.model.Employee;
import com.senior.hr.model.Manager;
import com.senior.hr.model.Warning;
import com.senior.hr.model.WarningType;
import com.senior.hr.repository.EmployeeRepository;
import com.senior.hr.repository.ManagerRepository;
import com.senior.hr.repository.WarningRepository;
import com.senior.hr.repository.WarningTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarningServiceImpl implements WarningService {
    private final WarningRepository warningRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final WarningTypeRepository warningTypeRepository;
    private final WarningMapper warningMapper;
    private final WarningTypeMapper warningTypeMapper;

    @Override
    public List<WarningDTO> listWarningByEmployee(String employeeUsername) {
        //Todo exceptionHandling
        Employee employee = employeeRepository.findByUsername(employeeUsername).orElseThrow();
        return warningRepository.findByEmployee(employee).stream().map(warningMapper::warningToWarningDTO).collect(Collectors.toList());
    }

    @Override
    public void DeleteWarningById(Long warningId) {
        warningRepository.deleteById(warningId);
    }

    @Override
    public void addWarning(WarningAddRequestDTO warningAddRequestDTO) {
        //Todo Exception handling
        Employee employee = employeeRepository.findByUsername(warningAddRequestDTO.getEmployeeUsername()).orElseThrow();
        Manager manager = managerRepository.findByUsername(warningAddRequestDTO.getManagerUsername()).orElseThrow();
        WarningType warningType = warningTypeRepository.findByName(warningAddRequestDTO.getWarningType()).orElseThrow();
        Warning warning = new Warning();
        warning.setDescription(warningAddRequestDTO.getDescription());
        warning.setWarningType(warningType);
        warning.setIssuerManager(manager);
        warning.setEmployee(employee);
        warningRepository.save(warning);
    }

    @Override
    public void addWarningType(WarningTypeDTO warningTypeDTO) {
        warningTypeRepository.save(warningTypeMapper.warningTypeDTOToWarningType(warningTypeDTO));
    }

    @Transactional
    @Override
    public void deleteWarningType(String warningTypeName) {
        //Todo add exception handling
        WarningType warningType = warningTypeRepository.findByName(warningTypeName).orElseThrow();
        //warningRepository.deleteWarningByWarningType(warningType);
        warningTypeRepository.deleteByName(warningTypeName);
    }

    @Override
    public List<WarningTypeDTO> listAllWarningType() {
        return warningTypeRepository.findAll().stream().map(warningTypeMapper::warningTypeToWarningTypeDTO).collect(Collectors.toList());
    }
}
