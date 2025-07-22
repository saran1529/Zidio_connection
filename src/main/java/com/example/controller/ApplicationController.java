package com.example.controller;

import com.example.DTO.ApplicationDTO;
import com.example.enums.Status;
import com.example.repository.ApplicationRepository;
import com.example.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @PostMapping("/apply")
    public ResponseEntity <ApplicationDTO>apply(@RequestBody ApplicationDTO dto) {
        return ResponseEntity.ok(applicationService.apply(dto));
    }

    @GetMapping("/student/{studentsId}")
    public ResponseEntity<List<ApplicationDTO>>getApplicationByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(applicationService.getApplicationByStudentId(studentId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationDTO>>getApplicationByJobId(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationByJobId(jobId));
    }

    @PostMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                             @RequestParam Status status) {
                    applicationService.updateStatus(id, status);
    }

    @GetMapping("internal/count")
    public ResponseEntity<Long>countApplication() {
        return ResponseEntity.ok(applicationRepository.count());
    }

}
