package com.senior.hr.Security;

import com.senior.hr.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final long JWT_EXPIRATION = 700000000;
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().toString();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("Subject", username);
        claimsMap.put("Role", role);
        Claims claims = Jwts.claims(claimsMap);
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt( new Date(System.currentTimeMillis()))
                .setExpiration(expireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        System.out.println("New token :");
        System.out.println(token);
        return token;
    }

    public String getUsernameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect",ex.fillInStackTrace());
        }
    }

    public String generateTokenFromUsername(String username, Role role) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("Subject", username);
        claimsMap.put("Role", "[" + role.getRoleName() + "]");
        Claims claims = Jwts.claims(claimsMap);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
