package com.senior.hr.DTO;

import com.senior.hr.model.Role;
import lombok.Data;

import java.sql.Date;

@Data
public class ApplicantDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String motherName;
    private String ssn;
    private String degree;
    private String placeOfBirth;
    private Date dateOfBirth;
    private String number;
    private String residence;
    private String prevCompany;
}
