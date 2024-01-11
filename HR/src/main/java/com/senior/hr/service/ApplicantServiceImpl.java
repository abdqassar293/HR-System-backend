package com.senior.hr.service;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.Security.RefreshTokenService;
import com.senior.hr.mapper.ApplicantMapper;
import com.senior.hr.model.Applicant;
import com.senior.hr.model.Role;
import com.senior.hr.repository.ApplicantRepository;
import com.senior.hr.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApplicantServiceImpl implements ApplicantService {
    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    @Override
    public void addApplicant(ApplicantDTO applicantDTO) {
        Applicant applicant = applicantMapper.applicantDTOToApplicant(applicantDTO);
        Optional<Role> roleOptional = roleRepository.findRoleByRoleName("Applicant");
        roleOptional.ifPresent(applicant::setRole);
        applicant.setPassword(passwordEncoder.encode(applicant.getPassword()));
        applicantMapper.applicantToApplicantDTO(applicantRepository.save(applicant));
    }

    @Override
    @Transactional
    public void deleteApplicantById(Long id) {
        refreshTokenService.deleteByUserId(id);
        applicantRepository.deleteById(id);
    }

    @Override
    public List<ApplicantDTO> findAllApplicant() {
        return applicantRepository.findAll().stream().map(applicantMapper::applicantToApplicantDTO).collect(Collectors.toList());
    }


}
