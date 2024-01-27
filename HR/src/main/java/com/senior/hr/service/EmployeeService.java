package com.senior.hr.service;

import com.senior.hr.DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> findAllEmployee();

    void deleteEmployeeByUsername(String username);

    EmployeeDTO editEmployeeInfo(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeByUsername(String username);
}
