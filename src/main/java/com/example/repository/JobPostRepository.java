package com.example.repository;

import com.example.enums.JobType;
import com.example.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    List<JobPost>findByPostedByEmail(String email);
    List<JobPost>findByJobTitle(String jobTitle);
    List<JobPost>findByJobType(JobType jobType);
    List<JobPost>findByCompanyName(String companyName);
    List<JobPost>findByJobDescription(String jobDescription);
    List<JobPost>findByJobLocation(String jobLocation);
    List<JobPost>findByPostedDate(Date PostedDate);
}
