package com.senior.hr.service;

import com.senior.hr.DTO.AddEmployeeToManagerRequest;
import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.DTO.ManagerDTO;
import com.senior.hr.DTO.ManagerResponseDTO;

import java.util.List;

public interface ManagerService {
    List<ManagerResponseDTO> listAllManagers();

    void deleteManagerByUsername(String username);

    ManagerDTO makaManager(String employeeUserName);

    void addEmployeeToManager(AddEmployeeToManagerRequest addEmployeeToManagerRequest);

    List<EmployeeDTO> listAllManagerEmployees(String managerUsername);

}
