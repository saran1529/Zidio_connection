package com.example.entity;

import com.example.enums.Status;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "applications")
public class Application
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long jobId;
    private String resumeURL;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date appliedDate;

    public Application() {}

    public Application(
            Long id,
            Long studentId,
            Long jobId,
            String resumeURL,
            Status status,
            Date appliedDate) {
                            this.id = id;
                            this.studentId = studentId;
                            this.jobId = jobId;
                            this.resumeURL = resumeURL;
                            this.status = status;
                            this.appliedDate = appliedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }
}
