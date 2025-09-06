package com.example.service;

import com.example.entity.AppUser;
import com.example.entity.AdminUser;
import com.example.repository.AppUserRepository;
import com.example.repository.AdminUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminUserRepository adminRepo;
    private final AppUserRepository appUserRepo;

    public AdminService(AdminUserRepository adminRepo, AppUserRepository appUserRepo) {
        this.adminRepo = adminRepo;
        this.appUserRepo = appUserRepo;
    }

    // Fetch all AppUsers
    public List<AppUser> getAllUsers() {
        return appUserRepo.findAll();
    }

    // Fetch user by ID
    public Optional<AppUser> getUserById(Long id) {
        return appUserRepo.findById(id);
    }

    // Delete user by ID
    public boolean deleteUser(Long id) {
        if (appUserRepo.existsById(id)) {
            appUserRepo.deleteById(id);
            return true;
        }
        return false;
    }

    // Update admin profile
    public AdminUser updateAdminProfile(AdminUser admin) {
        return adminRepo.save(admin);
    }

    // Get admin by email/username
    public Optional<AdminUser> getAdminByEmail(String email) {
        return adminRepo.findByUsername(email);
    }
}
