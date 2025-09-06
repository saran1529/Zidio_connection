package com.example.controller;

import com.example.service.FileUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/upload/resume")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> uploadResume(@RequestParam("file") MultipartFile file) {
        Map<String, Object> resp = new HashMap<>();
        try {
            String url = fileUploadService.uploadFile(file);

            resp.put("success", true);
            resp.put("message", "File uploaded successfully");
            resp.put("url", url);

            return ResponseEntity.ok(resp);

        } catch (IllegalArgumentException e) {
            resp.put("success", false);
            resp.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(resp);

        } catch (Exception e) {
            resp.put("success", false);
            resp.put("error", "Upload Failed: " + e.getMessage());
            return ResponseEntity.internalServerError().body(resp);
        }
    }
}
