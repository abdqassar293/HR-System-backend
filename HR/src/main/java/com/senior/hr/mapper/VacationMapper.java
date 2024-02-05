package com.senior.hr.mapper;

import com.senior.hr.DTO.VacationDTO;
import com.senior.hr.model.Vacation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class VacationMapper {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public Vacation vacationDTOToVacation(VacationDTO vacationDTO) {
        Vacation vacation = new Vacation();
        if (vacationDTO.getStartDate() != null && vacationDTO.getEndDate() != null) {
            vacation.setStartDate(LocalDate.parse(vacationDTO.getStartDate(), dtf));
            vacation.setEndDate(LocalDate.parse(vacationDTO.getEndDate(), dtf));
            vacation.setNumberOfDays(LocalDate.parse(vacationDTO.getStartDate(), dtf).datesUntil(LocalDate.parse(vacationDTO.getEndDate(), dtf)).toList().size());
        }
        vacation.setApproved(false);
        vacation.setPayed(vacationDTO.getPayed());
        return vacation;
    }

    public VacationDTO vacationToVacationDTO(Vacation vacation) {
        VacationDTO vacationDTO = new VacationDTO();
        vacationDTO.setId(vacation.getId());
        vacationDTO.setApproved(vacation.getApproved());
        LocalDate startDate = vacation.getStartDate();
        if (startDate != null) {
            vacationDTO.setStartDate(startDate.toString());
        }
        LocalDate endDate = vacation.getEndDate();
        if (endDate != null) {
            vacationDTO.setEndDate(endDate.toString());
        }
        vacationDTO.setNumberOfDays(vacation.getNumberOfDays());
        vacationDTO.setPayed(vacation.getPayed());
        vacationDTO.setEmployeeUsername(vacation.getEmployee().getUsername());
        return vacationDTO;
    }
}
