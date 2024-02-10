package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "screeningResults")
public class ScreeningResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double softwareDeveloper;
    private Double frontEnd;
    private Double networkAdmin;
    private Double webDeveloper;
    private Double projectManager;
    private Double databaseAdmin;
    private Double securityAnalyst;
    private Double systemAdmin;
    private Double pythonDeveloper;
    private Double javaDeveloper;
}
