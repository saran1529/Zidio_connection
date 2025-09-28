package com.example.security;

import com.example.repository.AdminUserRepository;
import com.example.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final AdminUserRepository adminUserRepository;
    private final StudentRepository studentRepository;

    public SecurityConfig(JWTUtil jwtUtil,
                          AdminUserRepository adminUserRepository,
                          StudentRepository studentRepository) {
        this.jwtUtil = jwtUtil;
        this.adminUserRepository = adminUserRepository;
        this.studentRepository = studentRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil, adminUserRepository, studentRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/register/student",
                                                  "/api/auth/register/recruiter").permitAll()
                        .requestMatchers("/api/auth/**", "/actuator/**").permitAll()

                        // Role-based endpoints
                        .requestMatchers("/api/students/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/api/jobPosts/**").hasAnyAuthority("ROLE_RECRUITER", "ROLE_ADMIN")
                        .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/analytics/**").hasAuthority("ROLE_ADMIN")

                        // Resume upload endpoints
                        .requestMatchers(HttpMethod.POST, "/api/upload/resume").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") // fixed
                        .requestMatchers("/api/upload/resume/my").hasAuthority("ROLE_USER")
                        .requestMatchers("/api/upload/resume/all").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/api/upload/resume/**").authenticated()

                        // Catch-all
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
