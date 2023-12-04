package com.senior.hr.mapper;

import com.senior.hr.DTO.ApplicantDTO;
import com.senior.hr.model.Applicant;

public class ApplicantMapper {
    public Applicant ApplicantDTOToApplicant(ApplicantDTO applicantDTO) {
        Applicant applicant = new Applicant();
        applicant.setFirstName(applicantDTO.getFirstName());
        applicant.setLastName(applicantDTO.getLastName());
        applicant.setDegree(applicantDTO.getDegree());
        applicant.setNumber(applicantDTO.getNumber());
        applicant.setFatherName(applicantDTO.getFatherName());
        applicant.setMotherName(applicantDTO.getMotherName());
        applicant.setSsn(applicantDTO.getSsn());
        applicant.setResidence(applicantDTO.getResidence());
        applicant.setPrevCompany(applicantDTO.getPrevCompany());
        applicant.setDateOfBirth(applicantDTO.getDateOfBirth());
        applicant.setPlaceOfBirth(applicantDTO.getPlaceOfBirth());
        applicant.setUsername(applicantDTO.getUsername());
        return applicant;
    }
}
