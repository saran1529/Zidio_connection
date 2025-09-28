package com.example.controller;

import com.example.DTO.EmailRequestDTO;
import com.example.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO request) {
        emailService.sendEmail(request);
        return ResponseEntity.ok("Email sent successfully");
    }
}
