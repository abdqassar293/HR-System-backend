package com.senior.hr.controller;

import com.senior.hr.DTO.VacancyDTO;
import com.senior.hr.service.VacancyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("listAll")
    public List<VacancyDTO> listAllVacancies() {
        return vacancyService.findAllVacancies();
    }

    @PostMapping("addNewVacancy")
    public void addNewVacancy(@RequestBody VacancyDTO vacancyDTO) {
        vacancyService.addVacancy(vacancyDTO);
    }

    @DeleteMapping("deleteVacancy")
    public void deleteVacancy(@RequestParam String vacancyID) {
        vacancyService.deleteVacancy(Long.valueOf(vacancyID));
    }
}
