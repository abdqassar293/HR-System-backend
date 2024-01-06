package com.senior.hr.mapper;

import com.senior.hr.DTO.WarningTypeDTO;
import com.senior.hr.model.WarningType;
import org.springframework.stereotype.Component;

@Component
public class WarningTypeMapper {
    public WarningType warningTypeDTOToWarningType(WarningTypeDTO warningTypeDTO) {
        WarningType warningType = new WarningType();
        warningType.setName(warningTypeDTO.getName());
        warningType.setMark(warningTypeDTO.getMark());
        return warningType;
    }

    public WarningTypeDTO warningTypeToWarningTypeDTO(WarningType warningType) {
        WarningTypeDTO warningTypeDTO = new WarningTypeDTO();
        warningTypeDTO.setId(warningType.getId());
        warningTypeDTO.setMark(warningType.getMark());
        warningTypeDTO.setName(warningType.getName());
        return warningTypeDTO;
    }
}
