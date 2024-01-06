package com.senior.hr.mapper;

import com.senior.hr.DTO.PositionDTO;
import com.senior.hr.model.Position;
import org.springframework.stereotype.Service;

@Service
public class PositionMapper {
    public Position positionDTOToPosition(PositionDTO positionDTO) {
        Position position = new Position();
        position.setPositionName(positionDTO.getName());
        return position;
    }

    public PositionDTO positionToPositionDTO(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setName(position.getPositionName());
        return positionDTO;
    }
}
