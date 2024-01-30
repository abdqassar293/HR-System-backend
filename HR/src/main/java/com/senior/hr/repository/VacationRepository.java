package com.senior.hr.repository;

import com.senior.hr.model.Employee;
import com.senior.hr.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    List<Vacation> findAllByApprovedIsTrue();

    List<Vacation> findAllByApprovedIsFalse();

    List<Vacation> findAllByEmployee(Employee employee);

    List<Vacation> findByEmployeeAndPayedIsTrueAndApprovedIsTrueAndStartYearMonth(Employee employee, String yearMonth);

    List<Vacation> findByEmployeeAndPayedIsTrueAndApprovedIsTrueAndEndYearMonth(Employee employee, String yearMonth);
    void deleteByEmployee(Employee employee);
}