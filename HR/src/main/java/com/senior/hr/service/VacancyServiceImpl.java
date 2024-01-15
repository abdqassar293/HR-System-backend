package com.senior.hr.service;

import com.senior.hr.DTO.VacancyDTO;
import com.senior.hr.mapper.VacancyMapper;
import com.senior.hr.model.Vacancy;
import com.senior.hr.repository.PositionRepository;
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
    private final PositionRepository positionRepository;

    @Override
    public void addVacancy(VacancyDTO vacancyDTO) {
        Vacancy vacancy = vacancyMapper.vacancyDTOToVacancy(vacancyDTO);
        vacancy.setJobTitle(positionRepository.findByPositionName(vacancyDTO.getJobTitle()).orElseThrow());
        vacancyRepository.save(vacancy);
    }

    @Override
    public void deleteVacancy(Long vacancyId) {
        vacancyRepository.deleteById(vacancyId);
    }

    @Override
    public List<VacancyDTO> findAllVacancies() {
        return vacancyRepository.findAll().stream().map(vacancyMapper::vacancyToVacancyDTO).collect(Collectors.toList());
    }

    @Override
    public VacancyDTO findVacancyById(Long vacancyId) {
        //Todo add exception handling
        return vacancyMapper.vacancyToVacancyDTO(vacancyRepository.findById(vacancyId).orElseThrow());
    }
}
