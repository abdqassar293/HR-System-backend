package com.senior.hr.repository;

import com.senior.hr.model.Employee;
import com.senior.hr.model.Warning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarningRepository extends JpaRepository<Warning, Long> {
    void deleteByEmployee(Employee employee);
}