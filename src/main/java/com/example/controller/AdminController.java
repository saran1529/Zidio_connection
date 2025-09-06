package com.example.controller;

import com.example.DTO.AdminUserDTO;
import com.example.enums.Role;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    // ✅ Constructor-based dependency injection (best practice)
    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ✅ Get all users
    @GetMapping("/users")
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    // ✅ Get users by role
    @GetMapping("/users/{role}")
    public ResponseEntity<List<AdminUserDTO>> getByRole(@PathVariable Role role) {
        return ResponseEntity.ok(adminService.findByRole(role));
    }

    // ✅ Update user status by email
    @PutMapping("/status/{email}")
    public ResponseEntity<AdminUserDTO> updateUserStatus(
            @PathVariable String email,
            @RequestParam boolean isActive) {
        return ResponseEntity.ok(adminService.updateStatus(email, isActive));
    }

    // ✅ Block user by ID
    @PostMapping("/users/block")
    public ResponseEntity<String> blockUser(@RequestParam Long id) {
        adminService.blockUser(id);
        return ResponseEntity.ok("User blocked successfully.");
    }

    // ✅ Unblock user by ID
    @PostMapping("/users/unblock")
    public ResponseEntity<String> unblockUser(@RequestParam Long id) {
        adminService.unBlockUser(id);
        return ResponseEntity.ok("User unblocked successfully.");
    }
}
