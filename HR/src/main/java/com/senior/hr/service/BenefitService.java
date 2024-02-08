package com.senior.hr.service;

import com.senior.hr.DTO.AddBenefitToEmployeeDTO;
import com.senior.hr.DTO.BenefitDTO;
import com.senior.hr.DTO.RegisterResponseDTO;

import java.util.List;

public interface BenefitService {
    List<BenefitDTO> listAllBenefits();

    BenefitDTO addNewBenefit(BenefitDTO benefitDTO);

    void deleteBenefitById(Long benefitId);

    void addBenefitToEmployee(AddBenefitToEmployeeDTO addBenefitToEmployeeDTO);
}
