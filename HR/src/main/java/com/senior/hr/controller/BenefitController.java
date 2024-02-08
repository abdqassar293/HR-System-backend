package com.senior.hr.controller;

import com.senior.hr.DTO.AddBenefitToEmployeeDTO;
import com.senior.hr.DTO.BenefitDTO;
import com.senior.hr.DTO.RegisterResponseDTO;
import com.senior.hr.service.BenefitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@RequestMapping("/api/benefit")
@AllArgsConstructor
public class BenefitController {
    private final BenefitService benefitService;

    @GetMapping("listAll")
    public List<BenefitDTO> listAllBenefits() {
        return benefitService.listAllBenefits();
    }

    @PostMapping("addBenefit")
    public BenefitDTO addNewBenefit(@RequestBody BenefitDTO benefitDTO) {
        return benefitService.addNewBenefit(benefitDTO);
    }

    @PostMapping("addBenefitToEmployee")
    public void newBenefitToEmployee(@RequestBody AddBenefitToEmployeeDTO AddBenefitToEmployeeDTO) {
        benefitService.addBenefitToEmployee(AddBenefitToEmployeeDTO);
    }

    @DeleteMapping("deleteBenefit")
    public void deleteBenefit(@RequestParam Long benefitId) {
        benefitService.deleteBenefitById(benefitId);
    }
}
