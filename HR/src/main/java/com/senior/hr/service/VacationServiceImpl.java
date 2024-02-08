package com.senior.hr.service;

import com.senior.hr.DTO.VacationDTO;
import com.senior.hr.mapper.VacationMapper;
import com.senior.hr.model.UserEntity;
import com.senior.hr.model.Vacation;
import com.senior.hr.repository.UserEntityRepository;
import com.senior.hr.repository.VacationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VacationServiceImpl implements VacationService {
    private final VacationRepository vacationRepository;
    private final UserEntityRepository employeeRepository;
    private final VacationMapper vacationMapper;

    @Override
    public List<VacationDTO> findAllApproved() {
        return vacationRepository.findAllByApprovedIsTrue().stream().map(vacationMapper::vacationToVacationDTO).collect(Collectors.toList());
    }

    @Override
    public List<VacationDTO> findAllByEmployee(String employeeUsername) {
        UserEntity employee = employeeRepository.findByUsername(employeeUsername).orElseThrow();
        return vacationRepository.findAllByEmployee(employee).stream().map(vacationMapper::vacationToVacationDTO).collect(Collectors.toList());
    }

    @Override
    public List<VacationDTO> findAllRequests() {
        return vacationRepository.findAllByApprovedIsFalse().stream().map(vacationMapper::vacationToVacationDTO).collect(Collectors.toList());
    }

    @Override
    public VacationDTO addVacationRequest(VacationDTO vacationDTO) {
        Vacation vacation = vacationMapper.vacationDTOToVacation(vacationDTO);
        vacation.setApproved(false);
        UserEntity employee = employeeRepository.findByUsername(vacationDTO.getEmployeeUsername()).orElseThrow();
        vacation.setEmployee(employee);
        vacation.setStartYearMonth(YearMonth.from(vacation.getStartDate()).toString());
        vacation.setEndYearMonth(YearMonth.from(vacation.getEndDate()).toString());
        return vacationMapper.vacationToVacationDTO(vacationRepository.save(vacation));
    }

    @Override
    public VacationDTO ApproveApplicationById(Long vacationId) {
        Vacation vacation = vacationRepository.findById(vacationId).orElseThrow();
        vacation.setApproved(true);
        return vacationMapper.vacationToVacationDTO(vacationRepository.save(vacation));
    }
}
