package com.example.DTO;

import com.example.enums.Status;

import java.util.Date;

public class ApplicationDTO {
    private Long id;
    private Long studentId;
    private Long jobId;
    private String resumeURL;
    private Status status;
    private Date appliedDate;

    public ApplicationDTO() {}

    public ApplicationDTO(
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

    public Long getStudentId() {
        return studentId;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public Status getStatus() {
        return status;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }
}
