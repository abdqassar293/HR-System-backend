package com.senior.hr.service;

import com.senior.hr.DTO.VacancyDTO;

import java.util.List;

public interface VacancyService {
    void addVacancy(VacancyDTO vacancyDTO);

    void deleteVacancy(Long vacancyId);

    List<VacancyDTO> findAllVacancies();
}
