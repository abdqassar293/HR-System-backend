package com.senior.hr.service;

import com.senior.hr.DTO.PositionDTO;

import java.util.List;

public interface PositionService {
    List<PositionDTO> listAllPosition();

    PositionDTO addNewPosition(PositionDTO positionDTO);

    void deletePositionById(Long positionId);
}
