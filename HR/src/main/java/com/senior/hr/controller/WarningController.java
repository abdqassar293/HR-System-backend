package com.senior.hr.controller;

import com.senior.hr.DTO.WarningAddRequestDTO;
import com.senior.hr.DTO.WarningDTO;
import com.senior.hr.DTO.WarningTypeDTO;
import com.senior.hr.service.WarningService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/warning")
public class WarningController {
    private final WarningService warningService;

    @GetMapping("listAllByEmployee")
    List<WarningDTO> listAllWarningByEmployee(@RequestParam String employeeUsername) {
        return warningService.listWarningByEmployee(employeeUsername);
    }

    @DeleteMapping("deleteWarning")
    public void deleteWarning(@RequestParam String warningId) {
        warningService.DeleteWarningById(Long.parseLong(warningId));
    }

    @PostMapping("newWarning")
    public void addNewWarning(@RequestBody WarningAddRequestDTO warningAddRequestDTO) {
        warningService.addWarning(warningAddRequestDTO);
    }

    @GetMapping("listAllWarningTypes")
    public List<WarningTypeDTO> listAllWarningType() {
        return warningService.listAllWarningType();
    }

    @PostMapping("addWarningType")
    public void addNewWarningType(@RequestBody WarningTypeDTO warningTypeDTO) {
        warningService.addWarningType(warningTypeDTO);
    }

    @DeleteMapping("deleteWarningType")
    public void deleteWarningType(@RequestParam String warningTypeName) {
        warningService.deleteWarningType(warningTypeName);
    }
}
