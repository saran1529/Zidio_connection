package com.example.controller;

import com.example.DTO.*;
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

    //  Single register method – service handles Admin/User distinction
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

    @PostMapping("/register/recruiter")
    public ResponseEntity<?> registerRecruiter(@RequestBody RecruiterRegisterRequestDTO requestDTO) {
        try {
            RecruiterDTO dto = authService.registerRecruiter(requestDTO);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(ex.getMessage()));
        }
    }

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
