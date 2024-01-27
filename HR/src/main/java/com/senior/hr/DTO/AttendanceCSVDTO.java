package com.senior.hr.DTO;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class AttendanceCSVDTO {
    @CsvBindByName
    private String username;
    @CsvBindByName
    private Integer day;
    @CsvBindByName
    private Integer month;
    @CsvBindByName
    private Integer year;
    @CsvBindByName
    private Double arrival;
    @CsvBindByName
    private Double departure;
}
