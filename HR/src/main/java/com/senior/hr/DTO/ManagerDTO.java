package com.senior.hr.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ManagerDTO {
    private Long Id;
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
    private String email;
    private Double salary;
    private String contractNumber;
    private List<BenefitDTO> benefits = new ArrayList<>();
}
