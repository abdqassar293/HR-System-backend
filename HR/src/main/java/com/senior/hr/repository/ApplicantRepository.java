package com.senior.hr.repository;

import com.senior.hr.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    void deleteById(Long id);
}