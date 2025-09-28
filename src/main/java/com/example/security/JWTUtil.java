package com.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    private final Key key;
    private final long expirationMs;

    public JWTUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration-ms}") long expirationMsProperty) {
        if (secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 characters long");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMsProperty;
    }

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    public String extractEmail(String token) {
        try {
            return getAllClaims(token).getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            return null;
        }
    }

    public List<String> extractRoles(String token) {
        try {
            Claims claims = getAllClaims(token);
            Object roles = claims.get("roles");
            if (roles instanceof List) {
                return ((List<?>) roles).stream()
                        .map(Objects::toString)
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getAllClaims(token);
            return !isTokenExpired(claims);
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return email != null && email.equals(userDetails.getUsername()) && validateToken(token);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}
