package com.example.DTO;

import com.example.enums.JobType;

import java.util.Date;

public class JobPostDTO
{
    public  Long id;
    public  String jobTitle;
    public  JobType jobType;
    public  String jobLocation;
    public  String jobDescription;
    public  String companyName;
    public  String postedByEmail;
    public Date postedDate;

    public JobPostDTO() {}
    public JobPostDTO(
            Long id,
            String jobTitle,
            JobType jobType,
            String jobDescription,
            String jobLocation,
            String companyName,
            String postedByEmail,
            Date postedDate) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.companyName = companyName;
        this.postedByEmail = postedByEmail;
        this.postedDate = postedDate;

    }

    public Long getId() {
        return id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public JobType getJobType() {
        return jobType;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPostedByEmail() {
        return postedByEmail;
    }

    public Date getPostedDate() {
        return postedDate;
    }
}
