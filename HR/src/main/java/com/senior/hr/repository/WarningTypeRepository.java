package com.senior.hr.repository;

import com.senior.hr.model.WarningType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarningTypeRepository extends JpaRepository<WarningType, Long> {
    Optional<WarningType> findByName(String name);

    void deleteByName(String name);
}