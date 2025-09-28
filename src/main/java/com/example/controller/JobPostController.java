package com.example.controller;

import com.example.DTO.JobPostDTO;
import com.example.enums.JobType;
import com.example.repository.JobPostRepository;
import com.example.service.JobPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPosts")
public class JobPostController {

    private final JobPostService jobPostService;
    private final JobPostRepository jobPostRepository;

    public JobPostController(JobPostService jobPostService, JobPostRepository jobPostRepository) {
        this.jobPostService = jobPostService;
        this.jobPostRepository = jobPostRepository;
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @PostMapping
    public ResponseEntity<JobPostDTO> createJob(@RequestBody JobPostDTO dto) {
        return ResponseEntity.ok(jobPostService.postJob(dto));
    }

    @GetMapping
    public ResponseEntity<List<JobPostDTO>> getAllJobs() {
        return ResponseEntity.ok(jobPostService.getAllJobPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostDTO> getJobById(@PathVariable Long id) {
        JobPostDTO dto = jobPostService.getJobPostById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @GetMapping("/recruiter")
    public ResponseEntity<List<JobPostDTO>> getByPostedEmail(@RequestParam String email) {
        return ResponseEntity.ok(jobPostService.getByRecruiterEmail(email));
    }

    @GetMapping("/jobTitle")
    public ResponseEntity<List<JobPostDTO>> getByJobTitle(@RequestParam String jobTitle) {
        return ResponseEntity.ok(jobPostService.getByJobTitle(jobTitle));
    }

    @GetMapping("/jobType/{jobType}")
    public ResponseEntity<List<JobPostDTO>> getByJobType(@PathVariable JobType jobType) {
        return ResponseEntity.ok(jobPostService.getByJobType(jobType));
    }

    @GetMapping("/companyName")
    public ResponseEntity<List<JobPostDTO>> getByCompanyName(@RequestParam String companyName) {
        return ResponseEntity.ok(jobPostService.getByCompanyName(companyName));
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @GetMapping("/internal/count")
    public ResponseEntity<Long> countJobPost() {
        return ResponseEntity.ok(jobPostRepository.count());
    }

    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobPost(@PathVariable Long id) {
        // First, get the job title to include in the message
        JobPostDTO job = jobPostService.getJobPostById(id);
        if (job == null) {
            return ResponseEntity.notFound().build();
        }

        // Delete the job
        jobPostService.deleteJobPost(id);

        // Return custom success message
        String message = "Job '" + job.getJobTitle() + "' has been deleted successfully!";
        return ResponseEntity.ok(message);
    }
}
