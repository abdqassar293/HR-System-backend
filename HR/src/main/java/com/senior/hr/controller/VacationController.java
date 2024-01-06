package com.senior.hr.controller;

import com.senior.hr.DTO.VacationDTO;
import com.senior.hr.service.VacationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/vacation")
public class VacationController {
    private final VacationService vacationService;

    @GetMapping("listAll")
    public List<VacationDTO> listAllRequests() {
        return vacationService.findAllRequests();
    }

    @GetMapping("listAllApproved")
    public List<VacationDTO> listAllApproved() {
        return vacationService.findAllApproved();
    }

    @GetMapping("listAllByEmployee")
    public List<VacationDTO> listAllByEmployee(@RequestParam String employeeUsername) {
        return vacationService.findAllByEmployee(employeeUsername);
    }

    @PostMapping("addNewRequest")
    public void addNewVacation(@RequestBody VacationDTO vacationDTO) {
        vacationService.addVacationRequest(vacationDTO);
    }

    @PostMapping("approveRequest")
    public void approveRequest(@RequestParam Long vacationId) {
        vacationService.ApproveApplicationById(vacationId);
    }
}
