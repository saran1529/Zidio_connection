package com.example.service;

import com.example.DTO.JobPostDTO;
import com.example.enums.JobType;
import com.example.entity.JobPost;
import com.example.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    // ✅ Constructor Injection (Recommended)
    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    public JobPostDTO postJob(JobPostDTO dto) {
        JobPost jobPost = new JobPost(
                dto.getId(),
                dto.getJobTitle(),
                dto.getJobType(),
                dto.getJobLocation(),
                dto.getJobDescription(),
                dto.getCompanyName(),
                dto.getPostedByEmail(),
                dto.getPostedDate());

        JobPost saved = jobPostRepository.save(jobPost);
        return mapToDTO(saved);
    }

    public List<JobPostDTO> getByPostedByEmail(String email) {
        return jobPostRepository.findByPostedByEmail(email)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<JobPostDTO> getByJobTitle(String jobTitle) {
        return jobPostRepository.findByJobTitle(jobTitle)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<JobPostDTO> getByJobType(JobType jobType) {
        return jobPostRepository.findByJobType(jobType)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<JobPostDTO> getByCompanyName(String companyName) {
        return jobPostRepository.findByCompanyName(companyName)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 🟡 The following methods are unused — keep only if you need them in future
    public List<JobPostDTO> getByJobDescription(String jobDescription) {
        return jobPostRepository.findByJobDescription(jobDescription)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<JobPostDTO> getByJobLocation(String jobLocation) {
        return jobPostRepository.findByJobLocation(jobLocation)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<JobPostDTO> getByPostedDate(Date postedDate) {
        return jobPostRepository.findByPostedDate(postedDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private JobPostDTO mapToDTO(JobPost jobPost) {
        return new JobPostDTO(
                jobPost.getId(),
                jobPost.getJobTitle(),
                jobPost.getJobType(),
                jobPost.getJobLocation(),
                jobPost.getJobDescription(),
                jobPost.getCompanyName(),
                jobPost.getPostedByEmail(),
                jobPost.getPostedDate());
    }
}
