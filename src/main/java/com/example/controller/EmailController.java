package com.example.controller;

import com.example.DTO.EmailRequestDTO;
import com.example.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle email sending requests.
 */
@RestController
@RequestMapping("/api/notify")
public class EmailController {

    private final EmailService emailService;

    /**
     * Constructor injection of EmailService.
     */
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Endpoint to send an email.
     * POST /api/notify/send
     *
     * @param request contains email details (to, subject, body)
     * @return HTTP 200 OK with success message
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO request) {
        emailService.sendEmail(request);
        return ResponseEntity.ok("Email sent successfully");
    }
}
