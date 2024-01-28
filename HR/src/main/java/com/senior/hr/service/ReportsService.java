package com.senior.hr.service;

import com.senior.hr.DTO.ReportsDTORequest;
import com.senior.hr.DTO.ReportsDTOResponse;

import java.util.List;

public interface ReportsService {
    void addNewReport(ReportsDTORequest reportsDTORequest);

    List<ReportsDTOResponse> findReportsByEmployee(String username);
}
