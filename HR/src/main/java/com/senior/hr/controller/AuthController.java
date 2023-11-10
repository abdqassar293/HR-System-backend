package com.senior.hr.controller;

import com.senior.hr.DTO.*;
import com.senior.hr.Security.RefreshTokenService;
import com.senior.hr.exception.TokenRefreshException;
import com.senior.hr.model.RefreshToken;
import com.senior.hr.model.UserEntity;
import com.senior.hr.Security.JwtService;
import com.senior.hr.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserEntityRepository userEntityRepository,
                          PasswordEncoder passwordEncoder, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        UserEntity userEntity = userEntityRepository.findByUsername(loginDto.getUsername()).orElse(new UserEntity());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userEntity.getId());
        return new ResponseEntity<>(new AuthResponseDTO(token, refreshToken.getToken()), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO registerDto) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        if (userEntityRepository.existsByUsername(registerDto.getUsername())) {
            registerResponseDTO.setMessage("Username is taken!");
            return new ResponseEntity<>(registerResponseDTO, HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        userEntityRepository.save(user);
        registerResponseDTO.setMessage("User registered successfully!");
        return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK);
    }

    @PostMapping("refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequestDTO request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtService.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponseDTO(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}
