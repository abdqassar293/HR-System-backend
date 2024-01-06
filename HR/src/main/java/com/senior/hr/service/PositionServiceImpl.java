package com.senior.hr.service;

import com.senior.hr.DTO.PositionDTO;
import com.senior.hr.mapper.PositionMapper;
import com.senior.hr.model.Position;
import com.senior.hr.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    @Override
    public List<PositionDTO> listAllPosition() {
        return positionRepository.findAll().stream().map(positionMapper::positionToPositionDTO).collect(Collectors.toList());
    }

    @Override
    public PositionDTO addNewPosition(PositionDTO positionDTO) {
        Position position = positionMapper.positionDTOToPosition(positionDTO);
        return positionMapper.positionToPositionDTO(positionRepository.save(position));
    }

    @Override
    public void deletePositionById(Long positionId) {
        positionRepository.deleteById(positionId);
    }
}
