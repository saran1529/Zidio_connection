package com.example.DTO;

public class AnalyticalResponseDTO {
    public Long students;
    public Long recruiters;
    public Long jobPosts;
    public Long auth;
    public Long applications;
    public Long admins;
    public String fileUpload;
    public String email;

    public AnalyticalResponseDTO() {}

    public AnalyticalResponseDTO(
            Long students,
            Long recruiters,
            Long jobPosts,
            Long auth,
            Long applications,
            Long admins,
            String fileUpload,
            String email
    ) {
        this.students = students;
        this.recruiters = recruiters;
        this.jobPosts = jobPosts;
        this.auth = auth;
        this.applications = applications;
        this.admins = admins;
        this.fileUpload = fileUpload;
        this.email = email;
    }
}
