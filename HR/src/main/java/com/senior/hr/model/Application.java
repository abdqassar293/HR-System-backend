package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "motivation_letter")
    @JdbcTypeCode(SqlTypes.CLOB)
    private String motivationLetter;

    @Column(name = "english_level")
    private String englishLevel;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Column(name = "application_date")
    private Date applicationDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PreviousProject> previousProjects = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @Column(name = "qualified_for_interview")
    private Boolean qualifiedForInterview;

    @Column(name = "interview_date")
    private Date interviewDate;
}