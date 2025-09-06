package com.example.controller;

import com.example.entity.Application;
import com.example.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Apply to a job
    @PostMapping("/apply")
    public ResponseEntity<Application> applyToJob(@RequestParam Long studentId,
                                                  @RequestParam Long jobPostId) {
        return ResponseEntity.ok(applicationService.applyToJob(studentId, jobPostId));
    }

    // Get all applications
    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        return ResponseEntity.ok(applicationService.getAllApplications());
    }

    // Get applications by student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Application>> getApplicationsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(applicationService.getApplicationsByStudent(studentId));
    }

    // Get applications by job post
    @GetMapping("/job/{jobPostId}")
    public ResponseEntity<List<Application>> getApplicationsByJob(@PathVariable Long jobPostId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobPostId));
    }

    // Update application status
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<Application> updateStatus(@PathVariable Long applicationId,
                                                    @RequestParam String status) {
        return ResponseEntity.ok(applicationService.updateStatus(applicationId, status));
    }
}
