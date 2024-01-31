package com.senior.hr.mapper;

import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.DTO.ManagerResponseDTO;
import com.senior.hr.model.Employee;
import com.senior.hr.model.Warning;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private final PositionMapper positionMapper;
    private final ManagerMapper managerMapper;
    private final BenefitMapper benefitMapper;
    private final WarningMapper warningMapper;

    public Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(employeeDTO.getPassword());
        employee.setDegree(employeeDTO.getDegree());
        employee.setNumber(employeeDTO.getNumber());
        employee.setFatherName(employeeDTO.getFatherName());
        employee.setResidence(employeeDTO.getResidence());
        employee.setSsn(employeeDTO.getSsn());
        employee.setSalary(employeeDTO.getSalary());
        if (employeeDTO.getDateOfBirth() != null) {
            employee.setDateOfBirth(Date.valueOf(employeeDTO.getDateOfBirth()));
        }
        employee.setMotherName(employeeDTO.getMotherName());
        //employee.setPosition(positionMapper.positionDTOToPosition(employeeDTO.getPosition()));
        //employee.setManager(managerMapper.managerDTOToManager(employeeDTO.getManagerDTO()));
        employee.setBenefits(employeeDTO.getBenefits().stream().map(benefitMapper::benefitDTOToBenefit).collect(Collectors.toList()));
        return employee;
    }

    public EmployeeDTO employeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setUsername(employee.getUsername());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setFatherName(employee.getFatherName());
        employeeDTO.setMotherName(employee.getMotherName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setSsn(employee.getSsn());
        employeeDTO.setPlaceOfBirth(employee.getPlaceOfBirth());
        employeeDTO.setDegree(employee.getDegree());
        employeeDTO.setNumber(employee.getNumber());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setEmail(employeeDTO.getEmail());
        Date date = employee.getDateOfBirth();
        if (date != null) {
            employeeDTO.setDateOfBirth(date.toString());
        }
        employeeDTO.setResidence(employee.getResidence());
        employeeDTO.setPosition(employee.getPosition().getPositionName());
        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO();
        managerResponseDTO.setMangerUsername(employee.getManager().getUsername());
        managerResponseDTO.setManagerFirstName(employee.getManager().getFirstName());
        managerResponseDTO.setManagerLastName(employee.getManager().getLastName());
        employeeDTO.setContractNumber("A234234");
        employeeDTO.setManagerDTO(managerResponseDTO);
        employeeDTO.setBenefits(employee.getBenefits().stream().map(benefitMapper::benefitToBenefitDTO).collect(Collectors.toList()));
        employeeDTO.setWarnings(employee.getWarnings().stream().map(warningMapper::warningToWarningDTO).collect(Collectors.toList()));
        return employeeDTO;
    }
}
