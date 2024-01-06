package com.senior.hr.mapper;

import com.senior.hr.DTO.BenefitDTO;
import com.senior.hr.model.Benefit;
import org.springframework.stereotype.Component;

@Component
public class BenefitMapper {
    public Benefit benefitDTOToBenefit(BenefitDTO benefitDTO) {
        Benefit benefit = new Benefit();
        benefit.setName(benefitDTO.getName());
        benefit.setCutPercentage(benefitDTO.getCutPercentage());
        return benefit;
    }

    public BenefitDTO benefitToBenefitDTO(Benefit benefit) {
        BenefitDTO benefitDTO = new BenefitDTO();
        benefitDTO.setId(benefit.getId());
        benefitDTO.setName(benefit.getName());
        benefitDTO.setCutPercentage(benefit.getCutPercentage());
        return benefitDTO;
    }
}
