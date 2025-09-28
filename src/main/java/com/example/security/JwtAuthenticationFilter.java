package com.example.security;

import com.example.repository.AdminUserRepository;
import com.example.repository.StudentRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final AdminUserRepository adminUserRepository;
    private final StudentRepository studentRepository;

    public JwtAuthenticationFilter(JWTUtil jwtUtil,
                                   AdminUserRepository adminUserRepository,
                                   StudentRepository studentRepository) {
        this.jwtUtil = jwtUtil;
        this.adminUserRepository = adminUserRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader  = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader  != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                if (jwtUtil.validateToken(token)) {
                    setAuthenticationFromToken(token);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
                    return;
                }
            } catch (Exception ex) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT validation failed: " + ex.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthenticationFromToken(String token) {
        String username = jwtUtil.extractUsername(token);

        List<String> roles = jwtUtil.extractRoles(token); // implement this in JWTUtil

        // Convert roles to SimpleGrantedAuthority
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
