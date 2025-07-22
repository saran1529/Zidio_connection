package com.example.controller;

import com.example.DTO.AdminUserDTO;
import com.example.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.enums.Role;
import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController
{

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<List<AdminUserDTO>> getByRole(
            @PathVariable Role role) {
        return ResponseEntity.ok(adminService.findByRole(role));
    }

    @PutMapping("/status/{email}")
    public ResponseEntity<AdminUserDTO> updateUserStatus(
            @PathVariable String email,
            @RequestParam boolean isActive) {
        return ResponseEntity.ok(adminService.updateStatus(email, isActive));
    }

    @PostMapping("/users/block")
    public  ResponseEntity<String> blockUser(
            @RequestParam Long id) {
        adminService.blockUser(id);
        return ResponseEntity.ok("user blocked successfully.");
    }

    @PostMapping("/users/unblock")
    public  ResponseEntity<String> unblockUser(
            @RequestParam Long id) {
        adminService.unBlockUser(id);
        return ResponseEntity.ok("user unblocked successfully.");
    }
}
