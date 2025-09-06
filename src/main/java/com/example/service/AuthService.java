package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // ✅ Constructor Injection (removes all field assignment warnings)
    public AuthService(UserRepository userRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new HashMap<>() {{
                put("message", "Email already registered");
            }});
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        return ResponseEntity.ok(new HashMap<>() {{
            put("token", token);
            put("message", "User Registered Successful");
        }});
    }

    public ResponseEntity<?> login(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isEmpty() || !passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.badRequest().body(new HashMap<>() {{
                put("message", "Invalid email or password");
            }});
        }

        String token = jwtUtil.generateToken(
                existingUser.get().getEmail(),
                existingUser.get().getRole().name());

        return ResponseEntity.ok(new HashMap<>() {{
            put("token", token);
            put("message", "Login successful");
        }});
    }
}
