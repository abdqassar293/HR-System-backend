package com.senior.hr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("3")
public class Manager extends UserEntity {
    private Double salary;
    private String contractNumber;
    @OneToMany(mappedBy = "manager", orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();
    @ManyToMany()
    @JoinTable(name = "manager_benefits",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "benefits_id"))
    private List<Benefit> benefits = new ArrayList<>();
}