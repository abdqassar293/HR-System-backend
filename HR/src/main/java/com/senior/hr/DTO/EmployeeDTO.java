package com.senior.hr.DTO;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String motherName;
    private String ssn;
    private String degree;
    private String placeOfBirth;
    private String dateOfBirth;
    private String number;
    private String residence;
    private PositionDTO position;
    private Double salary;
    private ManagerDTO managerDTO;
    private List<BenefitDTO> benefits = new ArrayList<>();
    private List<WarningDTO> warnings = new ArrayList<>();
}
