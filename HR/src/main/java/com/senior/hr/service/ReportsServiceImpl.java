package com.senior.hr.service;

import com.senior.hr.DTO.ReportsDTORequest;
import com.senior.hr.DTO.ReportsDTOResponse;
import com.senior.hr.model.Employee;
import com.senior.hr.model.Manager;
import com.senior.hr.model.ReportResultEnum;
import com.senior.hr.model.Report;
import com.senior.hr.repository.EmployeeRepository;
import com.senior.hr.repository.ManagerRepository;
import com.senior.hr.repository.ReportsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportsServiceImpl implements ReportsService {
    private final ReportsRepository reportsRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;

    @Override
    public void addNewReport(ReportsDTORequest reportsDTORequest) {
        Employee employee = employeeRepository.findByUsername(reportsDTORequest.getEmployeeUsername()).orElseThrow();
        Manager manager = managerRepository.findByUsername(reportsDTORequest.getManagerUsername()).orElseThrow();
        Report report = new Report();
        report.setEmployee(employee);
        report.setIssuer(manager);
        report.setDateIssued(Date.valueOf(LocalDate.now()));
        report.setReportDescription(reportsDTORequest.getReportDescription());
        report.setRating(1);
        report.setReport_result(ReportResultEnum.values()[report.getRating()]);
        reportsRepository.save(report);
    }

    @Transactional
    @Override
    public List<ReportsDTOResponse> findReportsByEmployee(String username) {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow();
        return reportsRepository.findByEmployee(employee).stream().map(report -> {
            ReportsDTOResponse reportsDTOResponse = new ReportsDTOResponse();
            reportsDTOResponse.setDateIssued(report.getDateIssued().toString());
            reportsDTOResponse.setReportResult(report.getReport_result().toString());
            reportsDTOResponse.setReportDescription(report.getReportDescription());
            reportsDTOResponse.setRating(report.getRating());
            reportsDTOResponse.setManagerUsername(report.getIssuer().getUsername());
            return reportsDTOResponse;
        }).toList();
    }
}
