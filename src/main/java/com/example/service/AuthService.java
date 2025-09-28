package com.example.service;

import com.example.DTO.*;
import com.example.entity.AdminUser;
import com.example.entity.AppUser;
import com.example.entity.Recruiter;
import com.example.enums.Role;
import com.example.repository.AdminUserRepository;
import com.example.repository.AppUserRepository;
import com.example.repository.RecruiterRepository;
import com.example.security.JWTUtil;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Optional;

@Service
public class AuthService {

    private final AdminUserRepository adminRepo;
    private final AppUserRepository appUserRepo;
    private final RecruiterRepository recruiterRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthService(AdminUserRepository adminrepo,
                       AppUserRepository appUserRepo,
                       RecruiterRepository recruiterRepo,
                       BCryptPasswordEncoder passwordEncoder,
                       JWTUtil jwtUtil) {
        this.adminRepo = adminrepo;
        this.appUserRepo = appUserRepo;
        this.recruiterRepo = recruiterRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /** ---------------- Registration ---------------- */
    public AuthResponse register(RegisterRequest request) {
        String role = request.getRole() != null ? request.getRole().toUpperCase() : "USER";
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        System.out.println("---- REGISTER DEBUG ----");
        System.out.println("Register email: " + request.getEmail());
        System.out.println("Register raw password: " + request.getPassword());

        if ("ROLE_ADMIN".equals(role)) {
            Optional<AdminUser> existingUser = adminRepo.findByUsername(request.getEmail());
            if (existingUser.isPresent()) throw new RuntimeException("Admin with this email already exists");

            AdminUser admin = new AdminUser();
            admin.setName(request.getName());
            admin.setUsername(request.getEmail());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setUserRole("ROLE_ADMIN");
            admin.setIsActive(true);

            AdminUser savedUser = adminRepo.save(admin);

            System.out.println("Stored encoded password (ADMIN): " + savedUser.getPassword());

            User springUser = new User(
                    savedUser.getUsername(),
                    savedUser.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(savedUser.getUserRole())));

            String token = jwtUtil.generateToken(springUser);

            return new AuthResponse(
                    savedUser.getName(),
                    savedUser.getUsername(),
                    savedUser.getUserRole(),
                    "Registration successful",
                    token
            );
        } else {
            Optional<AppUser> existingUser = appUserRepo.findByEmail(request.getEmail());
            if (existingUser.isPresent()) throw new RuntimeException("User with this email already exists");

            AppUser user = new AppUser();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(Role.ROLE_USER);
            user.setIsActive(true);

            AppUser saved = appUserRepo.save(user);

            System.out.println("Stored encoded password (USER): " + saved.getPassword());

            User springUser = new User(
                    saved.getEmail(),
                    saved.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );

            String token = jwtUtil.generateToken(springUser);

            return new AuthResponse(saved.getName(), saved.getEmail(), "ROLE_USER", "Registration successful", token);
        }
    }

    public AuthResponse login(LoginRequest request) {
        System.out.println("---- LOGIN DEBUG ----");
        System.out.println("Login email: " + request.getEmail());
        System.out.println("Login raw password: " + request.getPassword());

        // Check admin first
        Optional<AdminUser> adminOpt = adminRepo.findByUsername(request.getEmail());
        if (adminOpt.isPresent()) {
            AdminUser user = adminOpt.get();
            System.out.println("Stored encoded password (ADMIN): " + user.getPassword());

            boolean match = passwordEncoder.matches(request.getPassword(), user.getPassword());
            System.out.println("Password match? " + match);

            if (match) {
                User springUser = new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole()))
                );
                String token = jwtUtil.generateToken(springUser);
                return new AuthResponse(user.getName(), user.getUsername(), user.getUserRole(), "Login successful", token);
            } else {
                throw new RuntimeException("Invalid password");
            }
        }

        // Check AppUser next
        Optional<AppUser> userOpt = appUserRepo.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            AppUser user = userOpt.get();
            System.out.println("Stored encoded password (USER): " + user.getPassword());

            boolean match = passwordEncoder.matches(request.getPassword(), user.getPassword());
            System.out.println("Password match? " + match);

            if (match) {
                User springUser = new User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
                String token = jwtUtil.generateToken(springUser);
                return new AuthResponse(user.getName(), user.getEmail(), "ROLE_USER", "Login successful", token);
            } else {
                throw new RuntimeException("Invalid password");
            }
        }

        throw new RuntimeException("User not found");
    }

    public RecruiterDTO registerRecruiter(RecruiterRegisterRequestDTO requestDTO) {
        Optional<AdminUser> existingUser = adminRepo.findByUsername(requestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        AdminUser user = new AdminUser();
        user.setName(requestDTO.getName());
        user.setUsername(requestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setUserRole("ROLE_RECRUITER");
        user.setIsActive(true);
        adminRepo.save(user);

        Recruiter recruiter = new Recruiter();
        recruiter.setRecruiterName(requestDTO.getName());
        recruiter.setContactEmail(requestDTO.getEmail());
        recruiter.setCompanyName(requestDTO.getCompanyName());
        recruiter.setPhone(requestDTO.getPhone());
        recruiterRepo.save(recruiter);

        RecruiterDTO dto = new RecruiterDTO();
        dto.setId(recruiter.getId());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setContactEmail(recruiter.getContactEmail());
        dto.setPhone(recruiter.getPhone());

        return dto;
    }
}

