package com.senior.hr.mapper;

import com.senior.hr.DTO.VacancyDTO;
import com.senior.hr.model.JobTitleEnum;
import com.senior.hr.model.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class VacancyMapper {
    public Vacancy vacancyDTOToVacancy(VacancyDTO vacancyDTO) {
        Vacancy vacancy = new Vacancy();
        vacancy.setJobDescription(vacancyDTO.getJobDescription());
        vacancy.setYearsOfExperience(vacancyDTO.getYearsOfExperience());
        vacancy.setJobTitle(JobTitleEnum.valueOf(vacancyDTO.getJobTitle()));
        vacancy.setJobSalary(vacancyDTO.getJobSalary());
        return vacancy;
    }

    public VacancyDTO vacancyToVacancyDTO(Vacancy vacancy) {
        VacancyDTO vacancyDTO = new VacancyDTO();
        vacancyDTO.setId(vacancy.getId());
        vacancyDTO.setYearsOfExperience(vacancy.getYearsOfExperience());
        vacancyDTO.setJobSalary(vacancy.getJobSalary());
        vacancyDTO.setJobDescription(vacancy.getJobDescription());
        vacancyDTO.setJobTitle(vacancy.getJobTitle().toString());
        return vacancyDTO;
    }
}
