package com.senior.hr.repository;

import com.senior.hr.model.Employee;
import com.senior.hr.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Report, Long> {
    void deleteByEmployee(Employee employee);

    List<Report> findByEmployee(Employee employee);
}