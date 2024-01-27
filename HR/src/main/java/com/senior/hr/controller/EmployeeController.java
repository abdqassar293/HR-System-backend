package com.senior.hr.controller;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.senior.hr.DTO.AttendanceCSVDTO;
import com.senior.hr.DTO.EmployeeDTO;
import com.senior.hr.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("findByUsername")
    public EmployeeDTO findApplicantById(@RequestParam String username) {
        return employeeService.findEmployeeByUsername(username);
    }

    @GetMapping("listAll")
    public List<EmployeeDTO> listAllApplicants() {
        return employeeService.findAllEmployee();
    }

    @DeleteMapping("deleteEmployee")
    public void deleteEmployee(@RequestParam String username) {
        employeeService.deleteEmployeeByUsername(username);
    }

    @PostMapping("/uploadAttendance")
    public String uploadFile(@RequestParam("attendance") MultipartFile file) {
        if (file.isEmpty()) {
            return "No file is present";
        }
        try {
            InputStream inputStream = file.getInputStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));
            CsvToBean<AttendanceCSVDTO> csvToBean = new CsvToBeanBuilder<AttendanceCSVDTO>(reader)
                    .withType(AttendanceCSVDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<AttendanceCSVDTO> attendance = csvToBean.parse();
            log.error(attendance.toString());
            employeeService.addAttendance(attendance);
            return "File upload successful";
        } catch (IOException e) {
            return "File upload failed";
        }
    }

    @GetMapping("getAttendanceByMonth")
    public List<AttendanceCSVDTO> getAttendance(@RequestParam String username, @RequestParam String month, @RequestParam String year) {
        return employeeService.findAttendanceByEmployeeAndMonthAndYear(username, Integer.parseInt(month), Integer.parseInt(year));
    }
}
