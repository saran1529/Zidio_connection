package com.example.controller;

import com.example.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload/resume")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    // ✅ Constructor injection (better than field injection)
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            String url = fileUploadService.uploadFile(file);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Upload Failed: " + e.getMessage());
        }
    }
}
