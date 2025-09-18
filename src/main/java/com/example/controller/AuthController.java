package com.example.controller;

import com.example.DTO.LoginRequest;
import com.example.DTO.RegisterRequest;
import com.example.DTO.AuthResponse;
import com.example.service.AuthService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(ex.getMessage()));
        }
    }

    /** 🔹 Login an existing user */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try{
            AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(ex.getMessage()));
        }
}
        // Inner class for error responses
        public static class ErrorResponse {
            private String error;

            public ErrorResponse(String error) {
                this.error = error;
            }

            public String getError() {
                return error;
            }
            public void setError(String error) {
                this.error = error;
            }
        }

     }
