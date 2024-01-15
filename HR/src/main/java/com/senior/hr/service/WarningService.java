package com.senior.hr.service;

import com.senior.hr.DTO.WarningAddRequestDTO;
import com.senior.hr.DTO.WarningDTO;
import com.senior.hr.DTO.WarningTypeDTO;

import java.util.List;

public interface WarningService {
    List<WarningDTO> listWarningByEmployee(String employeeUsername);

    void DeleteWarningById(Long warningId);

    void addWarning(WarningAddRequestDTO warningAddRequestDTO);

    void addWarningType(WarningTypeDTO warningTypeDTO);

    void deleteWarningType(String warningTypeName);

    List<WarningTypeDTO> listAllWarningType();
}
