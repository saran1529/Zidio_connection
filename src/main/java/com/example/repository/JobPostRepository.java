package com.example.repository;

import com.example.entity.JobPost;
import com.example.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    List<JobPost> findByRecruiter_ContactEmail(String email);

    List<JobPost> findByJobTitleContainingIgnoreCase(String jobTitle);

    List<JobPost> findByJobType(JobType jobType);

    List<JobPost> findByCompanyNameContainingIgnoreCase(String companyName);

}
