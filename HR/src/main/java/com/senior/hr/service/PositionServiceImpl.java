package com.senior.hr.service;

import com.senior.hr.DTO.PositionDTO;
import com.senior.hr.mapper.PositionMapper;
import com.senior.hr.model.Employee;
import com.senior.hr.model.Position;
import com.senior.hr.repository.EmployeeRepository;
import com.senior.hr.repository.PositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<PositionDTO> listAllPosition() {
        return positionRepository.findAll().stream().map(position -> {
            PositionDTO positionDTO = positionMapper.positionToPositionDTO(position);
            positionDTO.setNumberOfEmployees(employeeRepository.findByPosition(position).size());
            return positionDTO;
        }).toList();
    }

    @Override
    public PositionDTO addNewPosition(PositionDTO positionDTO) {
        Position position = positionMapper.positionDTOToPosition(positionDTO);
        return positionMapper.positionToPositionDTO(positionRepository.save(position));
    }

    @Override
    public void deletePositionById(Long positionId) {
        Position position = positionRepository.findById(positionId).orElseThrow();
        positionRepository.deleteById(positionId);
    }
}
