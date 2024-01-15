package com.senior.hr.controller;

import com.senior.hr.DTO.AddEmployeeToManagerRequest;
import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.DTO.ManagerDTO;
import com.senior.hr.service.ManagerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@RequestMapping("/api/manager")
@AllArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("listAll")
    public List<ManagerDTO> listAllApplicants() {
        return managerService.listAllManagers();
    }

    @DeleteMapping("deleteManager")
    public void deleteManager(@RequestParam String username) {
        managerService.deleteManagerByUsername(username);
    }

    @PostMapping("makeManager")
    public ManagerDTO makeManager(@RequestParam String employeeUsername) {
        return managerService.makaManager(employeeUsername);
    }

    @PostMapping("addEmployeeToManager")
    public void addEmployeeToManager(@RequestBody AddEmployeeToManagerRequest addEmployeeToManagerRequest) {
        managerService.addEmployeeToManager(addEmployeeToManagerRequest);
    }

    @GetMapping("listEmployeesByManager")
    public List<EmployeeDTO> listEmployeeByManager(@RequestParam String managerUsername) {
        return managerService.listAllManagerEmployees(managerUsername);
    }
}
