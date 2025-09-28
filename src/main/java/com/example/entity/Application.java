package com.example.entity;

import com.example.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "job_post_id", nullable = false)
    private JobPost jobPost;

    @Column(nullable = false)
    private Status status;

    @Column(name = "resume_url")
    private String resumeURL;

    @Column(name = "applied_date", nullable = false)
    private LocalDateTime appliedDate = LocalDateTime.now();

    public Application() {}

    public Application(Student student, JobPost jobPost, Status status, String resumeURL) {
        this.student = student;
        this.jobPost = jobPost;
        this.status = status;
        this.resumeURL = resumeURL;
        this.appliedDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public JobPost getJobPost() { return jobPost; }
    public void setJobPost(JobPost jobPost) { this.jobPost = jobPost; }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResumeURL() {
        return resumeURL;
    }

    public void setResumeURL(String resumeURL) {
        this.resumeURL = resumeURL;
    }

    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
}
