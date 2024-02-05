package com.senior.hr.controller;

import com.senior.hr.DTO.*;
import com.senior.hr.Security.RefreshTokenService;
import com.senior.hr.exception.TokenRefreshException;
import com.senior.hr.model.RefreshToken;
import com.senior.hr.model.UserEntity;
import com.senior.hr.Security.JwtService;
import com.senior.hr.repository.UserEntityRepository;
import com.senior.hr.service.ApplicantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(maxAge = 3600)
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    private final UserEntityRepository userEntityRepository;

    private final ApplicantService applicantService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserEntityRepository userEntityRepository, JwtService jwtService, RefreshTokenService refreshTokenService, ApplicantService applicantService) {
        this.authenticationManager = authenticationManager;
        this.userEntityRepository = userEntityRepository;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.applicantService = applicantService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        UserEntity userEntity = userEntityRepository.findByUsername(loginDto.getUsername()).orElse(new UserEntity());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userEntity.getId());
        return new ResponseEntity<>(new AuthResponseDTO(token, refreshToken.getToken(), userEntity.getRole().getRoleName()), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody ApplicantDTO applicantDTO) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        if (userEntityRepository.existsByUsername(applicantDTO.getUsername())) {
            registerResponseDTO.setMessage("Username is taken!");
            return new ResponseEntity<>(registerResponseDTO, HttpStatus.BAD_REQUEST);
        }
        applicantService.addApplicant(applicantDTO);
        registerResponseDTO.setMessage("User registered successfully!");
        return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK);
    }

    @PostMapping("refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequestDTO request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration).map(RefreshToken::getUser).map(user -> {
            String token = jwtService.generateTokenFromUsername(user.getUsername(), user.getRole());
            log.error("formRefresh: " + token);
            return ResponseEntity.ok(new TokenRefreshResponseDTO(token, requestRefreshToken));
        }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not valid!"));
    }

    @PostMapping("isValid")
    public ResponseEntity<ValidityResponseDTO> isValid(@RequestBody ValidityRequestDTO validityRequestDTO) {
        String token = validityRequestDTO.getToken();
        Boolean isValid = jwtService.validateTokenForIsValid(token);
        ValidityResponseDTO validityResponseDTO = new ValidityResponseDTO();
        validityResponseDTO.setIsValid(isValid);
        if (isValid) {
            UserEntity userEntity = userEntityRepository.findByUsername(jwtService.getUsernameFromJWT(token)).orElseThrow();
            validityResponseDTO.setUsername(userEntity.getUsername());
            validityResponseDTO.setRole(userEntity.getRole().getRoleName());
        }
        return new ResponseEntity<>(validityResponseDTO, HttpStatus.OK);
    }
}
