package com.example.controller;

import com.example.DTO.RecruiterDTO;
import com.example.repository.RecruiterRepository;
import com.example.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController
{
    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @PostMapping("/register")
    public ResponseEntity <RecruiterDTO> register(@RequestBody RecruiterDTO dto) {
        return ResponseEntity.ok(recruiterService.createRecruiter(dto));
}

@GetMapping("/{email}")
    public ResponseEntity<RecruiterDTO>getEmail(@PathVariable String email) {
        return ResponseEntity.ok(recruiterService.getRecruiterByEmail(email));
}

@GetMapping("/{id}")
    public ResponseEntity<RecruiterDTO>getById(@PathVariable Long id) {
        return ResponseEntity.ok(recruiterService.getRecruiterById(id));
}

    @GetMapping("internal/count")
    public  ResponseEntity<Long>countRecruiters() {
        return ResponseEntity.ok(recruiterRepository.count());
    }
}
