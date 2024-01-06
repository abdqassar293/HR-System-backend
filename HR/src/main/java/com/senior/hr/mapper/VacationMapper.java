package com.senior.hr.mapper;

import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.DTO.VacationDTO;
import com.senior.hr.model.Vacation;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class VacationMapper {
    public Vacation vacationDTOToVacation(VacationDTO vacationDTO) {
        Vacation vacation = new Vacation();
        if (vacationDTO.getStartDate() != null) {
            vacation.setStartDate(Date.valueOf(vacationDTO.getStartDate()));
        }
        if (vacationDTO.getEndDate() != null) {
            vacation.setEndDate(Date.valueOf(vacationDTO.getEndDate()));
        }
        vacation.setApproved(vacationDTO.getApproved());
        vacation.setNumberOfDays(vacationDTO.getNumberOfDays());
        return vacation;
    }

    public VacationDTO vacationToVacationDTO(Vacation vacation) {
        VacationDTO vacationDTO = new VacationDTO();
        vacationDTO.setId(vacation.getId());
        vacationDTO.setApproved(vacation.getApproved());
        Date startDate = vacation.getStartDate();
        if (startDate != null) {
            vacationDTO.setStartDate(startDate.toString());
        }
        Date endDate = vacation.getEndDate();
        if (endDate != null) {
            vacationDTO.setEndDate(endDate.toString());
        }
        vacationDTO.setNumberOfDays(vacation.getNumberOfDays());
        vacationDTO.setPayed(vacation.getPayed());
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setUsername(vacation.getEmployee().getUsername());
        vacationDTO.setEmployeeDTO(employeeDTO);
        return vacationDTO;
    }
}
