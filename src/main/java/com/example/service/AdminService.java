package com.example.service;

import com.example.DTO.UpdateAppUserRequest;
import com.example.entity.AppUser;
import com.example.entity.AdminUser;
import com.example.enums.Role;
import com.example.repository.AppUserRepository;
import com.example.repository.AdminUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminUserRepository adminRepo;
    private final AppUserRepository appUserRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminUserRepository adminRepo,
                        AppUserRepository appUserRepo,
                        BCryptPasswordEncoder passwordEncoder) {
        this.adminRepo = adminRepo;
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------- USER METHODS ----------------
    public List<AppUser> getAllUsers() {
        return appUserRepo.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepo.findById(id);
    }

    public boolean deleteUser(Long id) {
        if (appUserRepo.existsById(id)) {
            appUserRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<AppUser> updateUser(Long id, UpdateAppUserRequest request) {
        return appUserRepo.findById(id).map(user -> {
            if (request.getName() != null) user.setName(request.getName());
            if (request.getEmail() != null) user.setEmail(request.getEmail());

            if (request.getRole() != null) {
                String roleStr = request.getRole().toUpperCase();
                if (!roleStr.startsWith("ROLE_")) {
                    roleStr = "ROLE_" + roleStr;
                }
                try {
                    user.setRole(Role.valueOf(roleStr));  //  convert String → Enum
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid role: " + request.getRole());
                }
            }

            if (request.getActive() != null) user.setIsActive(request.getActive());

            if (request.getPassword() != null && !request.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }
            return appUserRepo.save(user);
        });
    }

    // ---------------- ADMIN METHODS ----------------
    public List<AdminUser> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Optional<AdminUser> getAdminById(Long id) {
        return adminRepo.findById(id);
    }

    public Optional<AdminUser> getAdminByEmail(String email) {
        return adminRepo.findByUsername(email); // assuming username == email
    }

    public Optional<AdminUser> updateAdminById(Long id, AdminUser adminDetails) {
        return adminRepo.findById(id).map(existing -> {
            if (adminDetails.getName() != null) existing.setName(adminDetails.getName());
            if (adminDetails.getUsername() != null) existing.setUsername(adminDetails.getUsername());

            if (adminDetails.getPassword() != null && !adminDetails.getPassword().isBlank()) {
                existing.setPassword(passwordEncoder.encode(adminDetails.getPassword()));
            }

            if (adminDetails.getUserRole() != null) existing.setUserRole(adminDetails.getUserRole());
            if (adminDetails.getIsActive() != null) existing.setIsActive(adminDetails.getIsActive());

            return adminRepo.save(existing);
        });
    }

    /**
     * Update the currently logged-in admin profile
     */
    public Optional<AdminUser> updateCurrentAdmin(AdminUser existing, AdminUser updates) {
        if (updates.getName() != null) existing.setName(updates.getName());

        if (updates.getPassword() != null && !updates.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updates.getPassword()));
        }

        if (updates.getUserRole() != null) existing.setUserRole(updates.getUserRole());
        if (updates.getIsActive() != null) existing.setIsActive(updates.getIsActive());

        return Optional.of(adminRepo.save(existing));
    }

    public AdminUser saveAdmin(AdminUser admin) {
        return adminRepo.save(admin);
    }

    public boolean deleteAdmin(Long id) {
        if (adminRepo.existsById(id)) {
            adminRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
