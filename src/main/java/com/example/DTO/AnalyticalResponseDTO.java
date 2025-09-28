package com.example.DTO;

public class AnalyticalResponseDTO {
    private Long students;
    private Long recruiters;
    private Long jobPosts;
    private Long auth;
    private Long applications;
    private Long admins;
    private Long fileUploads;
    private Long emails;

    public AnalyticalResponseDTO() {
    }

    public AnalyticalResponseDTO(Long students,
                                 Long recruiters,
                                 Long jobPosts,
                                 Long auth,
                                 Long applications,
                                 Long admins,
                                 Long fileUploads,
                                 Long emails) {
        this.students = students;
        this.recruiters = recruiters;
        this.jobPosts = jobPosts;
        this.auth = auth;
        this.applications = applications;
        this.admins = admins;
        this.fileUploads = fileUploads;
        this.emails = emails;
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

    public Long getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(Long fileUploads) {
        this.fileUploads = fileUploads;
    }

    public Long getEmails() {
        return emails;
    }

    public void setEmails(Long emails) {
        this.emails = emails;
    }
}
