package com.senior.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private Integer day;
    private Integer month;
    private Integer year;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrival;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departure;
}
