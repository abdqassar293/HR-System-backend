package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "vacation_request")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date startDate;
    private Date endDate;
    private Boolean payed;
    private Integer numberOfDays;
    private Boolean approved;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}