package com.senior.hr.controller;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.DTO.RegisterResponseDTO;
import com.senior.hr.repository.RoleRepository;
import com.senior.hr.repository.UserEntityRepository;
import com.senior.hr.service.ApplicantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@RequestMapping("/api/applicant")
@AllArgsConstructor
public class ApplicantController {
    private final RoleRepository roleRepository;
    private final ApplicantService applicantService;
    private final UserEntityRepository userEntityRepository;

    @PostMapping("addNewApplicant")
    public ResponseEntity<RegisterResponseDTO> addNewApplicant(@RequestBody ApplicantDTO applicantDTO) {
        if (userEntityRepository.existsByUsername(applicantDTO.getUsername())) {
            RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
            registerResponseDTO.setMessage("username is taken");
            return new ResponseEntity<>(registerResponseDTO, HttpStatus.BAD_REQUEST);
        } else {
            applicantService.addApplicant(applicantDTO);
            RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
            registerResponseDTO.setMessage("registered successfully");
            return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK);
        }
    }

    @DeleteMapping("deleteApplicant")
    public void deleteApplicant(@RequestParam String applicantID) {
        applicantService.deleteApplicantById(Long.valueOf(applicantID));
    }

    @GetMapping("findByUsername")
    public ApplicantDTO findApplicantById(@RequestParam String username) {
        return applicantService.findApplicantByUsername(username);
    }

    @GetMapping("listAll")
    public List<ApplicantDTO> listAllApplicants() {
        return applicantService.findAllApplicant();
    }
}
