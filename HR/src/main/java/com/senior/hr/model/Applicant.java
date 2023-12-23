package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class Applicant extends UserEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "degree")
    private String degree;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "number")
    private String number;

    @Column(name = "residence")
    private String residence;

    @Column(name = "prev_company")
    private String prevCompany;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

}