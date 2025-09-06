package com.example.DTO;

public class AnalyticalResponseDTO {
    private Long students;
    private Long recruiters;
    private Long jobPosts;
    private Long auth;
    private Long applications;
    private Long admins;
    private String fileUpload;
    private String email;

    public AnalyticalResponseDTO() {
        // No-arg constructor
    }

    public AnalyticalResponseDTO(Long students, Long recruiters, Long jobPosts, Long auth, Long applications, Long admins, String fileUpload, String email) {
        this.students = students;
        this.recruiters = recruiters;
        this.jobPosts = jobPosts;
        this.auth = auth;
        this.applications = applications;
        this.admins = admins;
        this.fileUpload = fileUpload;
        this.email = email;
    }

    public Long getStudents() {
        return students;
    }

    public void setStudents(Long students) {
        this.students = students;
    }

    public Long getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(Long recruiters) {
        this.recruiters = recruiters;
    }

    public Long getJobPosts() {
        return jobPosts;
    }

    public void setJobPosts(Long jobPosts) {
        this.jobPosts = jobPosts;
    }

    public Long getAuth() {
        return auth;
    }

    public void setAuth(Long auth) {
        this.auth = auth;
    }

    public Long getApplications() {
        return applications;
    }

    public void setApplications(Long applications) {
        this.applications = applications;
    }

    public Long getAdmins() {
        return admins;
    }

    public void setAdmins(Long admins) {
        this.admins = admins;
    }

    public String getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(String fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
