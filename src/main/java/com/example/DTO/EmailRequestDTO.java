package com.example.DTO;

public class EmailRequestDTO
{

    public String to;
    public String subject;
    public String body;

    public EmailRequestDTO() {}

    public EmailRequestDTO(
            String to,
            String subject,
            String body) {
                        this.to = to;
                        this.subject = subject;
                        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
