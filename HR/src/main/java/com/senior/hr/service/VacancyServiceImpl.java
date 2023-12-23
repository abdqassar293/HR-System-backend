package com.senior.hr.service;

import com.senior.hr.DTO.VacancyDTO;
import com.senior.hr.mapper.VacancyMapper;
import com.senior.hr.repository.VacancyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Override
    public void addVacancy(VacancyDTO vacancyDTO) {
        vacancyRepository.save(vacancyMapper.vacancyDTOToVacancy(vacancyDTO));
    }

    @Override
    public void deleteVacancy(Long vacancyId) {
        vacancyRepository.deleteById(vacancyId);
    }

    @Override
    public List<VacancyDTO> findAllVacancies() {
        return vacancyRepository.findAll().stream().map(vacancyMapper::vacancyToVacancyDTO).collect(Collectors.toList());
    }
}
