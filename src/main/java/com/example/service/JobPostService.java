package com.example.service;

import com.example.DTO.JobPostDTO;
import com.example.entity.JobPost;
import com.example.enums.JobType;
import com.example.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    /** 🔹 Create / Post a job */
    public JobPostDTO postJob(JobPostDTO dto) {
        JobPost job = new JobPost();
        job.setJobTitle(dto.getJobTitle());
        job.setJobType(dto.getJobType());
        job.setJobLocation(dto.getJobLocation());
        job.setJobDescription(dto.getJobDescription());
        job.setCompanyName(dto.getCompanyName());
        job.setPostedByEmail(dto.getPostedByEmail());
        job.setPostedDate(dto.getPostedDate() != null ? dto.getPostedDate() : new java.util.Date());

        JobPost savedJob = jobPostRepository.save(job);

        return mapToDTO(savedJob);
    }

    /** 🔹 Get jobs by recruiter email */
    public List<JobPostDTO> getByPostedByEmail(String email) {
        return jobPostRepository.findByPostedByEmail(email)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** 🔹 Get jobs by job title */
    public List<JobPostDTO> getByJobTitle(String jobTitle) {
        return jobPostRepository.findByJobTitleContainingIgnoreCase(jobTitle)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** 🔹 Get jobs by job type */
    public List<JobPostDTO> getByJobType(JobType jobType) {
        return jobPostRepository.findByJobType(jobType)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** 🔹 Get jobs by company name */
    public List<JobPostDTO> getByCompanyName(String companyName) {
        return jobPostRepository.findByCompanyNameContainingIgnoreCase(companyName)
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** 🔹 Map entity to DTO */
    private JobPostDTO mapToDTO(JobPost job) {
        JobPostDTO dto = new JobPostDTO();
        dto.setId(job.getId());
        dto.setJobTitle(job.getJobTitle());
        dto.setJobType(job.getJobType());
        dto.setJobLocation(job.getJobLocation());
        dto.setJobDescription(job.getJobDescription());
        dto.setCompanyName(job.getCompanyName());
        dto.setPostedByEmail(job.getPostedByEmail());
        dto.setPostedDate(job.getPostedDate());
        return dto;
    }
}
