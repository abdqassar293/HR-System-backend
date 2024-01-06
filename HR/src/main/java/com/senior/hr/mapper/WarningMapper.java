package com.senior.hr.mapper;

import com.senior.hr.DTO.WarningDTO;
import com.senior.hr.model.Warning;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WarningMapper {
    private final WarningTypeMapper warningTypeMapper;

    public Warning warningDTOToWarning(WarningDTO warningDTO) {
        Warning warning = new Warning();
        warning.setDescription(warningDTO.getDescription());
        warning.setWarningType(warningTypeMapper.warningTypeDTOToWarningType(warningDTO.getWarningType()));
        return warning;
    }

    public WarningDTO warningToWarningDTO(Warning warning) {
        WarningDTO warningDTO = new WarningDTO();
        warningDTO.setId(warning.getId());
        warningDTO.setDescription(warning.getDescription());
        warningDTO.setWarningType(warningTypeMapper.warningTypeToWarningTypeDTO(warning.getWarningType()));
        warningDTO.setEmployeeUsername(warning.getEmployee().getUsername());
        warningDTO.setIssuerManager(warning.getIssuerManager().getUsername());
        return warningDTO;
    }
}
