package com.senior.hr.repository;

import com.senior.hr.model.Employee;
import com.senior.hr.model.Warning;
import com.senior.hr.model.WarningType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarningRepository extends JpaRepository<Warning, Long> {
    void deleteByEmployee(Employee employee);

    List<Warning> findByEmployee(Employee employee);

    void deleteWarningByWarningType(WarningType warningType);
}