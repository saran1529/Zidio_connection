package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_logs")
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;

    private String subject;

    private LocalDateTime sentAt;

    public EmailLog() {}

    public EmailLog(String recipient, String subject, LocalDateTime sentAt) {
        this.recipient = recipient;
        this.subject = subject;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
