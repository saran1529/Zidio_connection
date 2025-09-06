package com.example.controller;

import com.example.entity.AdminUser;
import com.example.entity.AppUser;
import com.example.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Admin-specific operations.
 * Base path: /api/admin
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /** ✅ Get all users (students, recruiters, admins) */
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    /** ✅ Get user by ID */
    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return adminService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** ✅ Delete a user */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = adminService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /** ✅ Update admin profile */
    @PutMapping("/profile")
    public ResponseEntity<AdminUser> updateAdminProfile(@RequestBody AdminUser admin) {
        AdminUser updated = adminService.updateAdminProfile(admin);
        return ResponseEntity.ok(updated);
    }

    /** ✅ Get admin profile by email */
    @GetMapping("/profile/{email}")
    public ResponseEntity<AdminUser> getAdminProfile(@PathVariable String email) {
        return adminService.getAdminByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
