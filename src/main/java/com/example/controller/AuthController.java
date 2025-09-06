package com.example.controller;

import com.example.DTO.LoginRequest;
import com.example.DTO.RegisterRequest;
import com.example.DTO.AuthResponse;
import com.example.entity.AdminUser;
import com.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** 🔹 Register a new user */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AdminUser user = authService.register(request);

        AuthResponse response = new AuthResponse(
                user.getName(),
                user.getUsername(),   // matches AdminUser entity
                user.getUserRole(),   // matches AdminUser entity
                "Registration successful"
        );

        return ResponseEntity.ok(response);
    }

    /** 🔹 Login an existing user */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AdminUser user = authService.login(request);

        AuthResponse response = new AuthResponse(
                user.getName(),
                user.getUsername(),
                user.getUserRole(),
                "Login successful"
        );

        return ResponseEntity.ok(response);
    }
}
