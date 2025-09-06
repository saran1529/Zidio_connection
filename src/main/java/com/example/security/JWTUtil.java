package com.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private final Key key;
    private final long expirationMs;

    public JWTUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration-ms}") long expirationMsProperty) {
        // ensure secret length large enough
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMsProperty;
    }

    public String generateToken(String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key)
                .compact();
    }

    // ✅ Extract subject (email) from token
    public String extractEmail(String token) {
        return getSubject(token);
    }

    public String getSubject(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return !isTokenExpired(claims.getBody());
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }

        // ✅ Validate token with user details
        boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
            String email = extractEmail(token);
            return email.equals(userDetails.getUsername()) && validateToken(token);
        }

        // --- Helper: check expiry ---
        boolean isTokenExpired(Claims claims) {
            return claims.getExpiration().before(new Date());
        }
    }
}
