package com.senior.hr.service;

import com.senior.hr.DTO.VacationDTO;

import java.util.List;

public interface VacationService {
    List<VacationDTO> findAllApproved();

    List<VacationDTO> findAllByEmployee(String employeeUsername);

    List<VacationDTO> findAllRequests();

    VacationDTO addVacationRequest(VacationDTO vacationDTO);

    VacationDTO ApproveApplicationById(Long vacationId);
}
