package com.example.controller;

import com.example.DTO.RecruiterDTO;
import com.example.entity.Recruiter;
import com.example.service.RecruiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<RecruiterDTO> createRecruiter(@RequestBody RecruiterDTO dto) {
        RecruiterDTO saved = recruiterService.createRecruiter(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecruiterDTO> getRecruiterById(@PathVariable Long id) {
        return recruiterService.getRecruiterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<RecruiterDTO> getRecruiterByEmail(@PathVariable String email) {
        return recruiterService.getRecruiterByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<RecruiterDTO>> getAllRecruiters() {
        return ResponseEntity.ok(recruiterService.getAllRecruiters());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruiterDTO> updateRecruiter(
            @PathVariable Long id,
            @RequestBody RecruiterDTO dto
    ) {
        RecruiterDTO updated = recruiterService.updateRecruiter(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecruiter(@PathVariable Long id) {
        recruiterService.deleteRecruiter(id);
        return ResponseEntity.noContent().build();
    }
}
