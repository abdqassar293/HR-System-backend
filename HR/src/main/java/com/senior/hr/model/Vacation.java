package com.senior.hr.model;

import com.vladmihalcea.hibernate.type.basic.YearMonthIntegerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
@Entity
@Table(name = "vacation")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean payed;
    private Integer numberOfDays;
    private Boolean approved;
    private String startYearMonth;
    private String endYearMonth;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}