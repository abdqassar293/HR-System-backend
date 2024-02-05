package com.senior.hr.mapper;

import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.DTO.ManagerResponseDTO;
import com.senior.hr.DTO.ReportsDTOResponse;
import com.senior.hr.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private final BenefitMapper benefitMapper;
    private final WarningMapper warningMapper;

    /*public Employee employeeDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(employeeDTO.getPassword());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setFatherName(employeeDTO.getFatherName());
        employee.setMotherName(employeeDTO.getMotherName());
        employee.setSsn(employeeDTO.getSsn());
        employee.setNumber(employeeDTO.getNumber());
        employee.setResidence(employeeDTO.getResidence());
        employee.setDegree(employeeDTO.getDegree());
        employee.setSalary(employeeDTO.getSalary());
        employee.setEmail(employeeDTO.getEmail());
        employee.setContractNumber(employee.getContractNumber());
        return employee;
    }*/

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
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setContractNumber(employeeDTO.getContractNumber());
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
        employeeDTO.setReports(employee.getReports().stream().map(report -> {
            ReportsDTOResponse reportsDTOResponse = new ReportsDTOResponse();
            reportsDTOResponse.setDateIssued(report.getDateIssued().toString());
            reportsDTOResponse.setReportResult(report.getReport_result().toString());
            reportsDTOResponse.setReportDescription(report.getReportDescription());
            reportsDTOResponse.setRating(report.getRating());
            reportsDTOResponse.setManagerUsername(report.getIssuer().getUsername());
            return reportsDTOResponse;
        }).toList());
        return employeeDTO;
    }
}
