package com.senior.hr.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("3")
public class Manager extends UserEntity {
    @OneToMany(mappedBy = "manager", orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

}