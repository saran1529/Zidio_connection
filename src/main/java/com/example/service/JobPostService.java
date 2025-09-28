package com.example.service;

import com.example.DTO.JobPostDTO;
import com.example.entity.JobPost;
import com.example.entity.Recruiter;
import com.example.enums.JobType;
import com.example.repository.JobPostRepository;
import com.example.repository.RecruiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final RecruiterRepository recruiterRepository;

    public JobPostService(JobPostRepository jobPostRepository,
                          RecruiterRepository recruiterRepository) {
        this.jobPostRepository = jobPostRepository;
        this.recruiterRepository = recruiterRepository;
    }

    /** Create / Post a job */
    public JobPostDTO postJob(JobPostDTO dto) {
        JobPost job = new JobPost();
        job.setJobTitle(dto.getJobTitle());
        job.setJobType(dto.getJobType());
        job.setJobLocation(dto.getJobLocation());
        job.setJobDescription(dto.getJobDescription());
        job.setCompanyName(dto.getCompanyName());
        job.setSalary(dto.getSalary());
        job.setMinExperience(dto.getMinExperience());
        job.setMaxExperience(dto.getMaxExperience());
        job.setSkills(dto.getSkills());
        job.setApplicationDeadline(dto.getApplicationDeadline());
        job.setPostedDate(dto.getPostedDate() != null ? dto.getPostedDate() : new java.util.Date());

        Recruiter recruiter = recruiterRepository
                .findByContactEmail(dto.getPostedByEmail()) // dto still contains postedByEmail
                .orElseThrow(() -> new IllegalArgumentException(
                        "Recruiter not found with email: " + dto.getPostedByEmail()
                ));
        job.setRecruiter(recruiter);

        JobPost savedJob = jobPostRepository.save(job);
        return mapToDTO(savedJob);
    }

    /** Get all jobs */
    public List<JobPostDTO> getAllJobPosts() {
        return jobPostRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** Get job by ID */
    public JobPostDTO getJobPostById(Long id) {
        return jobPostRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    /** Get jobs by recruiter email */
    public List<JobPostDTO> getByRecruiterEmail(String email) {
        return jobPostRepository.findByRecruiter_ContactEmail(email)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** Get jobs by job title */
    public List<JobPostDTO> getByJobTitle(String jobTitle) {
        return jobPostRepository.findByJobTitleContainingIgnoreCase(jobTitle)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** Get jobs by job type */
    public List<JobPostDTO> getByJobType(JobType jobType) {
        return jobPostRepository.findByJobType(jobType)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /** Get jobs by company name */
    public List<JobPostDTO> getByCompanyName(String companyName) {
        return jobPostRepository.findByCompanyNameContainingIgnoreCase(companyName)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Delete a job post by ID
    public void deleteJobPost(Long id) {
        jobPostRepository.deleteById(id);
    }

    /** Map entity to DTO using constructor */
    private JobPostDTO mapToDTO(JobPost job) {
        return new JobPostDTO(
                job.getId(),
                job.getJobTitle(),
                job.getJobType(),
                job.getJobLocation(),
                job.getJobDescription(),
                job.getCompanyName(),
                job.getRecruiter().getContactEmail(), // recruiter email
                job.getSalary(),
                job.getMinExperience(),
                job.getMaxExperience(),
                job.getSkills(),
                job.getApplicationDeadline(),
                job.getPostedDate()
        );
    }
}
