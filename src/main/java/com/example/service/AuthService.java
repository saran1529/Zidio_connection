package com.example.service;

import com.example.DTO.AuthResponse;
import com.example.DTO.RegisterRequest;
import com.example.DTO.LoginRequest;
import com.example.entity.AdminUser;
import com.example.repository.AdminUserRepository;
import com.example.security.JWTUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AdminUserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthService(AdminUserRepository repo,
                       BCryptPasswordEncoder passwordEncoder,
                       JWTUtil jwtUtil) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Register new user
    public AuthResponse register(RegisterRequest request) {

        Optional<AdminUser> existingUser = repo.findByUsername(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        AdminUser user = new AdminUser();
        user.setName(request.getName());
        user.setUsername(request.getEmail()); // using email field as username
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserRole(request.getRole() != null ? request.getRole() : "USER"); // role -> userRole
        user.setIsActive(true);

        AdminUser savedUser = repo.save(user);

        String token = jwtUtil.generateToken(savedUser.getUsername());

        return new AuthResponse(
                savedUser.getName(),
                savedUser.getUsername(),
                savedUser.getUserRole(),
                "Registration successful",
                token
        );
    }

    // Login
    public AuthResponse login(LoginRequest request) {
        Optional<AdminUser> userOpt = repo.findByUsername(request.getEmail());
        // again using email field as username
        if (userOpt.isPresent()) {
            AdminUser user = userOpt.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername());

                return new AuthResponse(
                        user.getName(),
                        user.getUsername(),
                        user.getUserRole(),
                        "Login successful",
                        token
                );
            } else {
                throw new RuntimeException("Invalid password");
            }
        }
        throw new RuntimeException("User not found");
    }
}
