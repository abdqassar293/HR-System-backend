package com.senior.hr.service;

import com.senior.hr.DTO.*;

import java.util.List;

public interface ManagerService {
    List<ManagerResponseDTO> listAllManagers();

    List<ManagerDTO> listManagers();

    void deleteManagerByUsername(String username);

    ManagerDTO makaManager(MakeManagerRequestDTO makeManagerRequestDTO);

    void addEmployeeToManager(AddEmployeeToManagerRequest addEmployeeToManagerRequest);

    List<EmployeeDTO> listAllManagerEmployees(String managerUsername);

}
