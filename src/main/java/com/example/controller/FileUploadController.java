package com.example.controller;

import com.example.entity.FileUpload;
import com.example.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/upload/resume")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    // ========================
    // Upload Resume (Student)
    // ========================
    @PostMapping
    public ResponseEntity<Map<String, Object>> uploadResume(@RequestParam("file") MultipartFile file,
                                                            Authentication authentication) {
        Map<String, Object> resp = new HashMap<>();
        try {
            String email = authentication.getName(); // logged-in user's email
            String url = fileUploadService.uploadFile(file, email);

            resp.put("success", true);
            resp.put("message", "Resume uploaded successfully");
            resp.put("url", url);
            return ResponseEntity.ok(resp);

        } catch (IllegalArgumentException e) {
            resp.put("success", false);
            resp.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(resp);

        } catch (IOException e) {
            resp.put("success", false);
            resp.put("error", "Upload failed: " + e.getMessage());
            return ResponseEntity.internalServerError().body(resp);
        }
    }

    // ========================
    // View My Resumes (Student)
    // ========================
    @GetMapping("/my")
    public ResponseEntity<List<FileUpload>> getMyResumes(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(fileUploadService.getFilesByEmail(email));
    }

    // ========================
    // View All Resumes (Admin)
    // ========================
    @GetMapping("/all")
    public ResponseEntity<?> getAllResumes(Authentication authentication) {
        if (!isAdmin(authentication)) {
            return ResponseEntity.status(403).body("Access denied: Only admins can view all resumes.");
        }
        return ResponseEntity.ok(fileUploadService.getAllFiles());
    }

    // ========================
    // Get Resume by ID
    // ========================
    @GetMapping("/{id}")
    public ResponseEntity<?> getResumeById(@PathVariable Long id, Authentication authentication) {
        Optional<FileUpload> fileOpt = fileUploadService.getFileById(id);

        if (fileOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Resume not found with id " + id);
        }

        FileUpload file = fileOpt.get();
        // Check authorization
        if (!isAdmin(authentication) && !file.getUploadedBy().equals(authentication.getName())) {
            return ResponseEntity.status(403).body("Access denied: You can only view your own resume.");
        }

        return ResponseEntity.ok(file);
    }

    // ========================
    // Delete Resume by ID
    // ========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteResume(@PathVariable Long id,
                                                            Authentication authentication) {
        Map<String, Object> resp = new HashMap<>();
        Optional<FileUpload> fileOpt = fileUploadService.getFileById(id);
        if (fileOpt.isEmpty()) {
            resp.put("success", false);
            resp.put("error", "Resume not found with id " + id);
            return ResponseEntity.status(404).body(resp);
        }

        FileUpload file = fileOpt.get();
        // Only admin or the uploader can delete
        if (!isAdmin(authentication) && !file.getUploadedBy().equals(authentication.getName())) {
            resp.put("success", false);
            resp.put("error", "Access denied: You cannot delete this resume.");
            return ResponseEntity.status(403).body(resp);
        }

        try {
            boolean deleted = fileUploadService.deleteFile(id);
            if (deleted) {
                resp.put("success", true);
                resp.put("message", "Resume deleted successfully");
                return ResponseEntity.ok(resp);
            } else {
                resp.put("success", false);
                resp.put("error", "Delete failed");
                return ResponseEntity.status(500).body(resp);
            }
        } catch (IOException e) {
            resp.put("success", false);
            resp.put("error", "Delete failed: " + e.getMessage());
            return ResponseEntity.internalServerError().body(resp);
        }
    }

    // ========================
    // Utility: Check if user is Admin
    // ========================
    private boolean isAdmin(Authentication authentication) {
        Set<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return roles.contains("ROLE_ADMIN");
    }
}
