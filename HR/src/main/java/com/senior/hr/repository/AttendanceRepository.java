package com.senior.hr.repository;

import com.senior.hr.model.Attendance;
import com.senior.hr.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAttendanceByEmployeeAndMonthAndYear(Employee employee, Integer month, Integer year);

    List<Attendance> findAttendanceByEmployeeAndYear(Employee employee, Integer year);
}