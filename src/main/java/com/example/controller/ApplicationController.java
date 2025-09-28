package com.example.controller;

import com.example.DTO.ApplicationDTO;
import com.example.entity.Application;
import com.example.enums.Status;
import com.example.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // ---------------- Apply for a job ----------------
    @PostMapping("/apply")
    public ResponseEntity<ApplicationDTO> applyToJob(@RequestParam Long studentId,
                                                     @RequestParam Long jobPostId) {
        Application app = applicationService.applyToJob(studentId, jobPostId);
        return ResponseEntity.ok(toDTO(app));
    }

    // ---------------- Get all applications ----------------
    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAllApplications() {
        List<ApplicationDTO> dtos = applicationService.getAllApplications()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ---------------- Get applications by student ----------------
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByStudent(@PathVariable Long studentId) {
        List<ApplicationDTO> dtos = applicationService.getApplicationsByStudent(studentId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ---------------- Get applications by job ----------------
    @GetMapping("/job/{jobPostId}")
    public ResponseEntity<List<ApplicationDTO>> getApplicationsByJob(@PathVariable Long jobPostId) {
        List<ApplicationDTO> dtos = applicationService.getApplicationsByJob(jobPostId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ---------------- Update application status ----------------
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationDTO> updateStatus(@PathVariable Long applicationId,
                                                       @RequestParam String status) {
        // Convert string to enum before passing to service
        Status statusEnum = Status.valueOf(status.toUpperCase());
        Application app = applicationService.updateStatus(applicationId, statusEnum);
        return ResponseEntity.ok(toDTO(app));
    }

    // ---------------- Dedicated endpoints for specific enum actions ----------------
    @PutMapping("/{applicationId}/accept")
    public ResponseEntity<ApplicationDTO> acceptApplication(@PathVariable Long applicationId) {
        Application app = applicationService.acceptApplication(applicationId);
        return ResponseEntity.ok(toDTO(app));
    }

    @PutMapping("/{applicationId}/reject")
    public ResponseEntity<ApplicationDTO> rejectApplication(@PathVariable Long applicationId) {
        Application app = applicationService.rejectApplication(applicationId);
        return ResponseEntity.ok(toDTO(app));
    }

    @PutMapping("/{applicationId}/close")
    public ResponseEntity<ApplicationDTO> closeApplication(@PathVariable Long applicationId) {
        Application app = applicationService.closeApplication(applicationId);
        return ResponseEntity.ok(toDTO(app));
    }

    // ---------------- Mapper ----------------
    private ApplicationDTO toDTO(Application app) {
        Date appliedDate = app.getAppliedDate() != null
                ? Date.from(app.getAppliedDate().atZone(ZoneId.systemDefault()).toInstant())
                : null;

        return new ApplicationDTO(
                app.getId(),
                app.getStudent() != null ? app.getStudent().getId() : null,
                app.getJobPost() != null ? app.getJobPost().getId() : null,
                app.getResumeURL(),
                app.getStatus() != null ? app.getStatus().name() : null,
                appliedDate
        );
    }
}
