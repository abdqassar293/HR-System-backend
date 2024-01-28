package com.senior.hr.service;

import com.senior.hr.DTO.AttendanceCSVDTO;
import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.DTO.SalaryCalculationRequestForOneEmployeeRequestDTO;
import com.senior.hr.DTO.SalaryCalculationResponseForOneEmployeeDTO;
import com.senior.hr.mapper.EmployeeMapper;
import com.senior.hr.model.Attendance;
import com.senior.hr.model.Employee;
import com.senior.hr.model.RealSalary;
import com.senior.hr.model.Vacation;
import com.senior.hr.repository.AttendanceRepository;
import com.senior.hr.repository.EmployeeRepository;
import com.senior.hr.repository.RealSalaryRepository;
import com.senior.hr.repository.VacationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final RealSalaryRepository realSalaryRepository;
    private final VacationRepository vacationRepository;

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

    @Override
    public SalaryCalculationResponseForOneEmployeeDTO calculateSalary(SalaryCalculationRequestForOneEmployeeRequestDTO request) {
        Employee employee = employeeRepository.findByUsername(request.getEmployeeUsername()).orElseThrow();
        Double hourSalary = employee.getSalary() / (22 * 9);
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByEmployeeAndMonthAndYear(employee, request.getMonth(), request.getYear());
        AtomicReference<Double> hourSum = new AtomicReference<>(0.0);
        attendanceList.forEach(attendance -> {
            Double hourInDay = attendance.getDeparture() - attendance.getArrival();
            hourSum.updateAndGet(v -> v + hourInDay);
        });
        AtomicReference<Integer> payedVacationSum = new AtomicReference<>(0);
        List<Vacation> payedVacations = vacationRepository.findByEmployeeAndPayedIsTrueAndApprovedIsTrue(employee);
        payedVacations.forEach(payedVacation -> {
            payedVacationSum.updateAndGet(v -> v + payedVacation.getNumberOfDays());
        });
        RealSalary realSalary = new RealSalary();
        realSalary.setBaseSalary(employee.getSalary());
        realSalary.setEmployee(employee);
        realSalary.setMonth(request.getMonth());
        realSalary.setYear(request.getYear());
        if (payedVacationSum.get() > 0) {
            hourSum.updateAndGet(v -> v + (payedVacationSum.get() * 9));
        }
        realSalary.setRealSalary(hourSalary * hourSum.get());
        RealSalary savedRealSalary = realSalaryRepository.save(realSalary);
        SalaryCalculationResponseForOneEmployeeDTO response = new SalaryCalculationResponseForOneEmployeeDTO();
        response.setEmployeeUsername(savedRealSalary.getEmployee().getUsername());
        response.setBaseSalary(savedRealSalary.getBaseSalary());
        response.setRealSalary(savedRealSalary.getRealSalary());
        return response;
    }


}
