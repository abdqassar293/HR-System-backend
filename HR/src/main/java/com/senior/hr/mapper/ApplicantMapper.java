package com.senior.hr.mapper;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.model.Applicant;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ApplicantMapper {
    public Applicant applicantDTOToApplicant(ApplicantDTO applicantDTO) {
        Applicant applicant = new Applicant();
        applicant.setUsername(applicantDTO.getUsername());
        applicant.setPassword(applicantDTO.getPassword());
        applicant.setFirstName(applicantDTO.getFirstName());
        applicant.setLastName(applicantDTO.getLastName());
        applicant.setDegree(applicantDTO.getDegree());
        applicant.setNumber(applicantDTO.getNumber());
        applicant.setFatherName(applicantDTO.getFatherName());
        applicant.setMotherName(applicantDTO.getMotherName());
        applicant.setSsn(applicantDTO.getSsn());
        applicant.setResidence(applicantDTO.getResidence());
        applicant.setEmail(applicantDTO.getEmail());
        Date dateOfBirth = Date.valueOf(applicantDTO.getDateOfBirth());
        applicant.setDateOfBirth(dateOfBirth);
        applicant.setPlaceOfBirth(applicantDTO.getPlaceOfBirth());
        return applicant;
    }

    public ApplicantDTO applicantToApplicantDTO(Applicant applicant) {
        ApplicantDTO applicantDTO = new ApplicantDTO();
        applicantDTO.setId(applicant.getId());
        applicantDTO.setUsername(applicant.getUsername());
        applicantDTO.setFirstName(applicant.getFirstName());
        applicantDTO.setLastName(applicant.getLastName());
        applicantDTO.setDegree(applicant.getDegree());
        applicantDTO.setNumber(applicant.getNumber());
        applicantDTO.setFatherName(applicant.getFatherName());
        applicantDTO.setMotherName(applicant.getMotherName());
        applicantDTO.setSsn(applicant.getSsn());
        applicantDTO.setResidence(applicant.getResidence());
        applicantDTO.setEmail(applicant.getEmail());
        Date date = applicant.getDateOfBirth();
        if (date != null) {
            applicantDTO.setDateOfBirth(date.toString());
        }
        applicantDTO.setPlaceOfBirth(applicant.getPlaceOfBirth());
        return applicantDTO;
    }
}
