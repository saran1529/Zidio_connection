package com.example.security;

import com.example.entity.AdminUser;
import com.example.repository.AdminUserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final AdminUserRepository adminUserRepository;

    public JwtAuthenticationFilter(JWTUtil jwtUtil, AdminUserRepository adminUserRepository) {
        this.jwtUtil = jwtUtil;
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

         try {
             if (jwtUtil.validateToken(token)) {
                 String username = jwtUtil.getSubject(token); // username = email
                 AdminUser user = adminUserRepository.findByUsername(username).orElse(null);

                 if (user != null && Boolean.TRUE.equals(user.getIsActive())) {
                     String role = user.getUserRole();
                 if (role != null && !role.startsWith("ROLE_")) {
                     role = "ROLE_" + role;
                 }
                     // Explicitly declare type to satisfy UsernamePasswordAuthenticationToken
                     List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                     UsernamePasswordAuthenticationToken authToken =
                             new UsernamePasswordAuthenticationToken(user, null, authorities);
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                 }

             } else {
                 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token");
                 return;
             }
         } catch (Exception ex) {
             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT validation failed: " + ex.getMessage());
         }
        }

        filterChain.doFilter(request, response);
    }
}
