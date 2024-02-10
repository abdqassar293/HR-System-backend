package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "benefits_type")
public class Benefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Double cutPercentage;

    @ManyToMany(mappedBy = "benefits")
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany(mappedBy = "benefits")
    private List<Manager> managers = new ArrayList<>();
}