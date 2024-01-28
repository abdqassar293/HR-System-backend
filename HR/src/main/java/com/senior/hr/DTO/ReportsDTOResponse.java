package com.senior.hr.DTO;

import lombok.Data;

@Data
public class ReportsDTOResponse {
    private String reportDescription;
    private String dateIssued;
    private String managerUsername;
    private Integer rating;
    private String reportResult;
}
