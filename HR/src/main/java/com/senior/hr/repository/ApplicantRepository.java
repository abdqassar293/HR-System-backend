package com.senior.hr.repository;

import com.senior.hr.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    void deleteById(Long id);

    Optional<Applicant> findByUsername(String username);
}