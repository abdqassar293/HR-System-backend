package com.senior.hr.repository;

import com.senior.hr.model.RefreshToken;
import com.senior.hr.model.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query("select r from refreshtoken r where r.token = ?1")
    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(UserEntity user);

    Optional<RefreshToken> findByUser(UserEntity user);

}