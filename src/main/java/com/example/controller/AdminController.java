package com.example.controller;

import com.example.DTO.UpdateAppUserRequest;
import com.example.entity.AdminUser;
import com.example.entity.AppUser;
import com.example.service.AdminService;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ---------------- USER ENDPOINTS ----------------
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        return adminService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = adminService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User with id" + id + "deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(
            @PathVariable Long id,
            @RequestBody UpdateAppUserRequest request) {

        Optional<AppUser> updatedUser = adminService.updateUser(id, request); // handled in service
        if (updatedUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(updatedUser.get());
    }

    // ---------------- LOGGED-IN ADMIN PROFILE ----------------
    @GetMapping("/profile")
    public ResponseEntity<AdminUser> getCurrentAdminProfile(Authentication authentication) {
        String email = authentication.getName(); // extracts from JWT (subject)
        return adminService.getAdminByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateCurrentAdminProfile(
            Authentication authentication,
            @RequestBody AdminUser adminDetails) {

        String email = authentication.getName();
        Optional<AdminUser> updatedAdmin = adminService.getAdminByEmail(email)
                .map(existing -> {
                    if (adminDetails.getName() != null) existing.setName(adminDetails.getName());
                    if (adminDetails.getPassword() != null && !adminDetails.getPassword().isBlank()) {
                        existing.setPassword(adminDetails.getPassword());
                    }
                    if (adminDetails.getUserRole() != null) existing.setUserRole(adminDetails.getUserRole());
                    if (adminDetails.getIsActive() != null) existing.setIsActive(adminDetails.getIsActive());
                    return adminService.saveAdmin(existing);
                });

        if (updatedAdmin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        return ResponseEntity.ok(updatedAdmin.get());
    }

    // ---------------- ADMIN MANAGEMENT ----------------
    @GetMapping("/admins")
    public ResponseEntity<List<AdminUser>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<AdminUser> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/admins/email/{email}")
    public ResponseEntity<AdminUser> getAdminByEmail(@PathVariable String email) {
        return adminService.getAdminByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<?> updateAdminById(
            @PathVariable Long id,
            @RequestBody AdminUser adminDetails) {

        Optional<AdminUser> updatedAdmin = adminService.updateAdminById(id, adminDetails);
        if (updatedAdmin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
        return ResponseEntity.ok(updatedAdmin.get());
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        boolean deleted = adminService.deleteAdmin(id);
        if (deleted) {
            return ResponseEntity.ok("Admin with id" + id + "deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
    }
}
