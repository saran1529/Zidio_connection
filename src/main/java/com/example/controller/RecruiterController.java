package com.example.controller;

import com.example.DTO.RecruiterDTO;
import com.example.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;

    @Autowired
    public RecruiterController(RecruiterService recruiterService) {
        this.recruiterService = recruiterService;
    }

    // ✅ Register a new recruiter
    @PostMapping("/register")
    public ResponseEntity<RecruiterDTO> register(@RequestBody RecruiterDTO dto) {
        return ResponseEntity.ok(recruiterService.createRecruiter(dto));
    }

    // ✅ Get recruiter by email
    @GetMapping("/email/{email}")
    public ResponseEntity<RecruiterDTO> getEmail(@PathVariable String email) {
        return ResponseEntity.ok(recruiterService.getRecruiterByEmail(email));
    }

    // ✅ Get recruiter by ID
    @GetMapping("/{id}")
    public ResponseEntity<RecruiterDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
    }

    // ✅ Get total number of recruiters
    @GetMapping("/internal/count")
    public ResponseEntity<Long> countRecruiters() {
        return ResponseEntity.ok(recruiterService.countRecruiters());
    }
}
