package com.senior.hr.repository;

import com.senior.hr.model.Applicant;
import com.senior.hr.model.Application;
import com.senior.hr.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByApplicant(Applicant applicant);

    List<Application> findAllByVacancy(Vacancy vacancy);

    List<Application> findAllByQualifiedForInterviewIsTrue();

    void deleteAllByApplicant(Applicant applicant);
}