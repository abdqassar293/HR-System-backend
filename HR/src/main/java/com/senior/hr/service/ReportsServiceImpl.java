package com.senior.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senior.hr.DTO.ReportsDTORequest;
import com.senior.hr.DTO.ReportsDTOResponse;
import com.senior.hr.model.Employee;
import com.senior.hr.model.Manager;
import com.senior.hr.model.Report;
import com.senior.hr.repository.EmployeeRepository;
import com.senior.hr.repository.ManagerRepository;
import com.senior.hr.repository.ReportsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ReportsServiceImpl implements ReportsService {
    private final ReportsRepository reportsRepository;
    private final EmployeeRepository employeeRepository;
    private final ManagerRepository managerRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void addNewReport(ReportsDTORequest reportsDTORequest) {
        Employee employee = employeeRepository.findByUsername(reportsDTORequest.getEmployeeUsername()).orElseThrow();
        Manager manager = managerRepository.findByUsername(reportsDTORequest.getManagerUsername()).orElseThrow();
        Report report = new Report();
        report.setEmployee(employee);
        report.setIssuer(manager);
        report.setDateIssued(Date.valueOf(LocalDate.now()));
        report.setReportDescription(reportsDTORequest.getReportDescription());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://192.168.1.105:1998/");
        builder.queryParam("text", reportsDTORequest.getReportDescription());
        String response = restTemplate.getForObject(builder.toUriString(), String.class);
        int classValue = 0;
        String className = "AI serverDown";
        try {
            Map<String, Object> map = null;
            map = objectMapper.readValue(response, Map.class);
            classValue = (int) map.get("class");
            className = (String) map.get("class_name");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        report.setRating(classValue);
        report.setReport_result(className);
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
