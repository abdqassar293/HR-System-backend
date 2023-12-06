package com.senior.hr.service;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.mapper.ApplicantMapper;
import com.senior.hr.model.Applicant;
import com.senior.hr.model.Role;
import com.senior.hr.repository.ApplicantRepository;
import com.senior.hr.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addApplicant(ApplicantDTO applicantDTO) {
        Applicant applicant = applicantMapper.applicantDTOToApplicant(applicantDTO);
        Optional<Role> roleOptional = roleRepository.findRoleByRoleName("Applicant");
        roleOptional.ifPresent(applicant::setRole);
        applicant.setPassword(passwordEncoder.encode(applicant.getPassword()));
        applicantMapper.applicantToApplicantDTO(applicantRepository.save(applicant));
    }

    @Override
    public void deleteApplicantById(Long id) {
        applicantRepository.deleteById(id);
    }
}
