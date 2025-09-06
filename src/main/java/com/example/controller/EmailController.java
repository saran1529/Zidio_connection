package com.example.controller;

import com.example.DTO.EmailRequestDTO;
import com.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class EmailController {

    private final EmailService emailService;

    // ✅ Constructor injection (recommended)
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody EmailRequestDTO request) {
        // ✅ Now using the request and calling email service
        emailService.sendEmail(request);
        return ResponseEntity.ok("Email sent successfully");
    }
}
