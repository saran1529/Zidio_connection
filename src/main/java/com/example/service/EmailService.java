package com.example.service;
import com.example.DTO.EmailRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(EmailRequestDTO req) {
        // For demo, simply print / log. Integrate JavaMailSender for real emails.
        System.out.println("Sending email to: " + req.getTo());
    }
}
