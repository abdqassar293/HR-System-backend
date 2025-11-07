package com.senior.hr.repository;

import com.senior.hr.model.Employee;
import com.senior.hr.model.RealSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RealSalaryRepository extends JpaRepository<RealSalary, Long> {
    List<RealSalary> findByEmployeeAndMonthAndYear(Employee employee, Integer month, Integer year);
    void deleteByEmployee(Employee employee);
}