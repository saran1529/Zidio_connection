package com.example.service;

import com.example.DTO.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailRequestDTO request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.to);
        message.setSubject(request.subject);
        message.setText(request.body);

        javaMailSender.send(message);
    }

}
