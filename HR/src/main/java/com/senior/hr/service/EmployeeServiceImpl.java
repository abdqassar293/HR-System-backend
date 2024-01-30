package com.senior.hr.service;

import com.senior.hr.DTO.AttendanceCSVDTO;
import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.DTO.SalaryCalculationResponseForOneEmployeeDTO;
import com.senior.hr.mapper.EmployeeMapper;
import com.senior.hr.model.*;
import com.senior.hr.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
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
    public SalaryCalculationResponseForOneEmployeeDTO calculateSalary(String username) {
        LocalDate date = LocalDate.now().minusMonths(1);
        log.error(date.toString());
        Employee employee = employeeRepository.findByUsername(username).orElseThrow();
        AtomicReference<Double> cutPercentage = new AtomicReference<>(0.0);
        employee.getBenefits().forEach(benefit -> cutPercentage.updateAndGet(v -> v + benefit.getCutPercentage()));
        double salaryAfterBenefits = employee.getSalary() - (employee.getSalary() * cutPercentage.get());
        double monthlyHourSalary = salaryAfterBenefits / (getWorkingDays(date.getMonthValue(), date.getYear()) * 9);
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByEmployeeAndMonthAndYear(employee, date.getMonth().getValue(), date.getYear());
        AtomicReference<Double> hourSum = new AtomicReference<>(0.0);
        attendanceList.forEach(attendance -> {
            Double hourInDay = attendance.getDeparture() - attendance.getArrival();
            hourSum.updateAndGet(v -> v + hourInDay);
        });
        List<Vacation> payedVacations = vacationRepository.findByEmployeeAndPayedIsTrueAndApprovedIsTrueAndStartYearMonth(employee, YearMonth.from(date).toString());
        AtomicReference<Integer> numberOfPayedVacationsDays = new AtomicReference<>(0);
        payedVacations.forEach(vacation -> numberOfPayedVacationsDays.updateAndGet(v -> v + daysBetweenDates(vacation.getStartDate(), vacation.getEndDate())));
        List<Vacation> payedVacations1 = vacationRepository.findByEmployeeAndPayedIsTrueAndApprovedIsTrueAndEndYearMonth(employee, YearMonth.from(date).toString());
        payedVacations1.forEach(vacation -> {
            if (!vacation.getStartYearMonth().equals(vacation.getEndYearMonth())) {
                numberOfPayedVacationsDays.updateAndGet(v -> v + daysBetweenDates(vacation.getEndDate().withDayOfMonth(1), vacation.getEndDate()));
            }
        });
        double payedVacationHours = numberOfPayedVacationsDays.get() * 9;
        double totalHours = hourSum.get() + payedVacationHours;
        Double payroll = totalHours * monthlyHourSalary;

        RealSalary realSalary = new RealSalary();
        realSalary.setBaseSalary(employee.getSalary());
        realSalary.setEmployee(employee);
        realSalary.setMonth(date.getMonth().getValue());
        realSalary.setYear(date.getYear());
        realSalary.setRealSalary(payroll);
        RealSalary savedRealSalary = realSalaryRepository.save(realSalary);
        SalaryCalculationResponseForOneEmployeeDTO response = new SalaryCalculationResponseForOneEmployeeDTO();
        response.setMonth(savedRealSalary.getMonth());
        response.setRealSalary(savedRealSalary.getRealSalary());
        return response;
    }

    private Integer getWorkingDays(Integer month, Integer year) {
        LocalDate date = LocalDate.of(year, month, 1);
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        int workingDays = 0;
        for (int i = 1; i <= daysInMonth; i++) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY) {
                workingDays++;
            }
            date = date.plusDays(1);
        }
        return workingDays;
    }

    private Integer daysBetweenDates(LocalDate date1, LocalDate date2) {
        if (date1.getMonth() == date2.getMonth()) {
            int days = 1;
            LocalDate current = date1;
            while (!current.isAfter(date2)) {
                if (current.getDayOfWeek().getValue() < 5) {
                    days++;
                }
                current = current.plusDays(1);
            }
            return days;
        } else {
            return daysBetweenDates(date1, date1.withDayOfMonth(date1.lengthOfMonth()));
        }
    }
}
