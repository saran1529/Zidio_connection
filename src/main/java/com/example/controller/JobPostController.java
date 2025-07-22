package com.example.controller;

import com.example.DTO.JobPostDTO;
import com.example.enums.JobType;
import com.example.repository.JobPostRepository;
import com.example.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPosts")
public class JobPostController
{
    @Autowired
    private JobPostService jobPostService;

    @Autowired
    private JobPostRepository jobPostRepository;

    @PostMapping
    public ResponseEntity<JobPostDTO>createJob(@RequestBody JobPostDTO dto){
        return ResponseEntity.ok(jobPostService.postJob(dto));
    }

    @GetMapping("/recruiter")
    public ResponseEntity<List<JobPostDTO>>getByPostedEmail(@RequestParam String email){
        return ResponseEntity.ok(jobPostService.getByPostedByEmail(email));
    }
    @GetMapping("/jobTitle")
    public ResponseEntity<List<JobPostDTO>>getByJobTitle(@RequestParam String jobTitle){
        return ResponseEntity.ok(jobPostService.getByJobTitle(jobTitle));
    }
    @GetMapping("/jobType/{jobType}")
    public ResponseEntity<List<JobPostDTO>>getByJobType(@PathVariable JobType jobType){
        return ResponseEntity.ok(jobPostService.getByJobType(jobType));
    }
    @GetMapping("/companyName")
    public ResponseEntity<List<JobPostDTO>>getByCompanyName(@RequestParam String companyName){
        return ResponseEntity.ok(jobPostService.getByCompanyName(companyName));
    }
    @GetMapping("internal/count")
    public  ResponseEntity<Long> countJobPost() {
        return ResponseEntity.ok(jobPostRepository.count());
    }


}
