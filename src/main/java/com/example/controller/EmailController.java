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
public class EmailController
{

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> send(
            @RequestBody EmailRequestDTO request) {
        return  ResponseEntity.ok("Email sent successfully");
    }
}
