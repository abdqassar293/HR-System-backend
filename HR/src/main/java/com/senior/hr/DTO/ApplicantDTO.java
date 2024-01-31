package com.senior.hr.DTO;

import lombok.Data;

@Data
public class ApplicantDTO {
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
    private String email;
}
