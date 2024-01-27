package com.senior.hr.service;

import com.senior.hr.DTO.AttendanceCSVDTO;
import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.mapper.EmployeeMapper;
import com.senior.hr.model.Attendance;
import com.senior.hr.model.Employee;
import com.senior.hr.repository.AttendanceRepository;
import com.senior.hr.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    @Override
    public List<EmployeeDTO> findAllEmployee() {
        return employeeRepository.findAll().stream().map(employeeMapper::employeeToEmployeeDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteEmployeeByUsername(String username) {
        employeeRepository.deleteByUsername(username);
    }

    @Override
    public EmployeeDTO editEmployeeInfo(EmployeeDTO employeeDTO) {
        return null;
    }

    @Override
    public EmployeeDTO findEmployeeByUsername(String username) {
        //Todo exception handling
        return employeeMapper.employeeToEmployeeDTO(employeeRepository.findByUsername(username).orElseThrow());
    }

    @Override
    public void addAttendance(List<AttendanceCSVDTO> attendanceCSVDTOS) {
        List<Attendance> attendanceList = attendanceCSVDTOS.stream().map(attendanceDTO -> {
            //Todo exception handling
            Employee employee = employeeRepository.findByUsername(attendanceDTO.getUsername()).orElseThrow();
            Attendance attendance = new Attendance();
            attendance.setEmployee(employee);
            attendance.setDay(attendanceDTO.getDay());
            attendance.setMonth(attendanceDTO.getMonth());
            attendance.setYear(attendanceDTO.getYear());
            attendance.setArrival(attendanceDTO.getArrival());
            attendance.setDeparture(attendanceDTO.getDeparture());
            return attendance;
        }).toList();
        attendanceRepository.saveAll(attendanceList);
    }

    @Override
    public List<AttendanceCSVDTO> findAttendanceByEmployeeAndMonthAndYear(String username, Integer month, Integer year) {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow();
        return attendanceRepository.findAttendanceByEmployeeAndMonthAndYear(employee, month, year).stream().map(attendance -> {
            AttendanceCSVDTO attendanceCSVDTO = new AttendanceCSVDTO();
            attendanceCSVDTO.setUsername(employee.getUsername());
            attendanceCSVDTO.setDay(attendance.getDay());
            attendanceCSVDTO.setMonth(month);
            attendanceCSVDTO.setYear(year);
            attendanceCSVDTO.setArrival(attendance.getArrival());
            attendanceCSVDTO.setDeparture(attendance.getDeparture());
            return attendanceCSVDTO;
        }).toList();
    }


}
