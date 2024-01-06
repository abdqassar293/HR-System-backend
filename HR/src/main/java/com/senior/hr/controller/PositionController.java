package com.senior.hr.controller;

import com.senior.hr.DTO.PositionDTO;
import com.senior.hr.service.PositionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/position")
public class PositionController {
    private final PositionService positionService;

    @GetMapping("listAll")
    public List<PositionDTO> listAllPositions() {
        return positionService.listAllPosition();
    }

    @PostMapping("addNewPosition")
    public PositionDTO addNewPosition(@RequestBody PositionDTO positionDTO) {
        return positionService.addNewPosition(positionDTO);
    }

    @DeleteMapping("deletePosition")
    public void deletePosition(@RequestParam Long positionId) {
        positionService.deletePositionById(positionId);
    }
}
