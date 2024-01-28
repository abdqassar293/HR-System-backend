package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer yearsOfExperience;
    private Double jobSalary;
    @Column(name = "job_description")
    @JdbcTypeCode(SqlTypes.CLOB)
    private String jobDescription;
    @OneToMany(mappedBy = "vacancy", orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position jobTitle;
    @Enumerated(EnumType.STRING)
    private JobTypeEnum jobType;

}
