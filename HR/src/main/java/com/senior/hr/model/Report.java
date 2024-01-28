package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;

@Getter
@Setter
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @JdbcTypeCode(SqlTypes.CLOB)
    private String reportDescription;
    @Enumerated(EnumType.STRING)
    private ReportResultEnum report_result;
    private Integer rating;
    private Date dateIssued;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private Manager issuer;

}
