package com.example.DTO;

import com.example.enums.Status;
import java.util.Date;

public class ApplicationDTO {
    private Long id;
    private Long studentId;
    private Long jobId;
    private String resumeURL;
    private String status;
    private Date appliedDate;

    public ApplicationDTO() {
    }

    public ApplicationDTO(Long id,
                          Long studentId,
                          Long jobId,
                          String resumeURL,
                          String status,
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }
}
