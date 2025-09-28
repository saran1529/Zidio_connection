package com.example.DTO;

import com.example.enums.JobType;
import java.util.Date;
import java.util.List;

public class JobPostDTO {
    private Long id;
    private String jobTitle;
    private JobType jobType;
    private String jobLocation;
    private String jobDescription;
    private String companyName;
    private String postedByEmail;
    private Double salary;
    private Integer minExperience;
    private Integer maxExperience;
    private List<String> skills;
    private Date applicationDeadline;
    private Date postedDate;

    public JobPostDTO() {
    }

    public JobPostDTO(Long id,
                      String jobTitle,
                      JobType jobType,
                      String jobLocation,
                      String jobDescription,
                      String companyName,
                      String postedByEmail,
                      Double salary,
                      Integer minExperience,
                      Integer maxExperience,
                      List<String> skills,
                      Date applicationDeadline,
                      Date postedDate) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.jobLocation = jobLocation;
        this.jobDescription = jobDescription;
        this.companyName = companyName;
        this.postedByEmail = postedByEmail;
        this.salary = salary;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        this.skills = skills;
        this.applicationDeadline = applicationDeadline;
        this.postedDate = postedDate;
    }

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

    public String getPostedByEmail() {
        return postedByEmail;
    }

    public void setPostedByEmail(String postedByEmail) {
        this.postedByEmail = postedByEmail;
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
}
