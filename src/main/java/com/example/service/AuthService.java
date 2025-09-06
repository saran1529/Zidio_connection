package com.example.service;

import com.example.DTO.RegisterRequest;
import com.example.DTO.LoginRequest;
import com.example.entity.AdminUser;
import com.example.repository.AdminUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final AdminUserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AdminUserRepository repo, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // Register new user
    public AdminUser register(RegisterRequest request) {
        AdminUser user = new AdminUser();
        user.setName(request.getName());
        user.setUsername(request.getEmail()); // using email field as username
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserRole(request.getRole()); // role -> userRole

        return repo.save(user);
    }

    // Login
    public AdminUser login(LoginRequest request) {
        Optional<AdminUser> userOpt = repo.findByUsername(request.getEmail());
        // again using email field as username
        if (userOpt.isPresent()) {
            AdminUser user = userOpt.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return user; // valid login
            }
        }
        throw new RuntimeException("Invalid username/email or password");
    }
}
