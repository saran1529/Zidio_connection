package com.example.entity;

import com.example.enums.JobStatus;
import com.example.enums.JobType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "job_posts", indexes = {
        @Index(name = "idx_job_location", columnList = "jobLocation"),
        @Index(name = "idx_job_type", columnList = "jobType")
})

public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 100)
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobType jobType;

    @Column(nullable = false)
    private String jobLocation;

    @Column(length = 2000, nullable = false)
    private String jobDescription;

    @Column(nullable = false)
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;

    @Column
    @Positive
    private Double salary;

    private Integer minExperience;
    private Integer maxExperience;

    @ElementCollection
    private List<String> skills = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private JobStatus status = JobStatus.OPEN;

    @Temporal(TemporalType.DATE)
    private Date applicationDeadline;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public JobPost() {}

    @PrePersist
    protected void onCreate() {
        this.postedDate = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // --- getters and setters ---


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Date getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(Date applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
