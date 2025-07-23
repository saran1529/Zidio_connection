package com.example.service;

import com.example.DTO.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    // Constructor injection to avoid "never assigned" warning
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    // Public method to send email
    public void sendEmail(EmailRequestDTO request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to);
        message.setSubject(request.subject);
        message.setText(request.body);

        javaMailSender.send(message);
    }

    // Optional: Log init for debug clarity
    @PostConstruct
    public void init() {
        System.out.println("EmailService initialized.");
    }
}
