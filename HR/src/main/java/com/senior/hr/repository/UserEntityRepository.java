package com.senior.hr.repository;

import com.senior.hr.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE public.user_entity SET user_type = '2' WHERE id = ?", nativeQuery = true)
    void updateUserToEmployee(Long id);
}