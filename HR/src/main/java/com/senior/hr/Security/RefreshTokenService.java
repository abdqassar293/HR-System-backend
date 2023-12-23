package com.senior.hr.Security;

import com.senior.hr.exception.TokenRefreshException;
import com.senior.hr.model.RefreshToken;
import com.senior.hr.model.UserEntity;
import com.senior.hr.repository.RefreshTokenRepository;
import com.senior.hr.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class RefreshTokenService {
    private static final Long refreshTokenDurationMs = 86400000L;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserEntityRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserEntityRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    public RefreshToken createRefreshToken(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userEntity);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        Optional<RefreshToken> refreshTokenOptional = refreshTokenRepository.findByUser(userEntity);
        if (refreshTokenOptional.isPresent()) {
            int bool = deleteByUserId(userId);
            if (bool == 1) {
                refreshToken = refreshTokenRepository.save(refreshToken);
            }
        } else {
            refreshToken = refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
