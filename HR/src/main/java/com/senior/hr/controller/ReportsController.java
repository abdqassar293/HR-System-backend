package com.senior.hr.controller;

import com.senior.hr.DTO.ReportsDTORequest;
import com.senior.hr.DTO.ReportsDTOResponse;
import com.senior.hr.service.ReportsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/reports")
public class ReportsController {
    private final ReportsService reportsService;

    @PostMapping("addNewReport")
    public void addNewReport(@RequestBody ReportsDTORequest reportsDTORequest) {
        reportsService.addNewReport(reportsDTORequest);
    }

    @GetMapping("findReportsByEmployee")
    public List<ReportsDTOResponse> findReportsByEmployee(@RequestParam String employeeUsername) {
        return reportsService.findReportsByEmployee(employeeUsername);
    }
}
