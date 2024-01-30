package com.senior.hr.repository;

import com.senior.hr.model.Benefit;
import com.senior.hr.model.Employee;
import com.senior.hr.model.Position;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    List<Employee> findByPosition(Position position);

    @Transactional
    void deleteByUsername(String username);
}