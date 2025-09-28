package com.example.service;

import com.example.entity.Application;
import com.example.entity.Student;
import com.example.entity.JobPost;
import com.example.enums.Status;
import com.example.repository.ApplicationRepository;
import com.example.repository.StudentRepository;
import com.example.repository.JobPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobPostRepository jobPostRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              StudentRepository studentRepository,
                              JobPostRepository jobPostRepository) {
        this.applicationRepository = applicationRepository;
        this.studentRepository = studentRepository;
        this.jobPostRepository = jobPostRepository;
    }

    // ---------------- Apply for a job ----------------
    public Application applyToJob(Long studentId, Long jobPostId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new RuntimeException("Job post not found"));

        // Default status = APPLIED
        Application application = new Application(student, jobPost, Status.APPLIED, null);

        return applicationRepository.save(application);
    }

    // ---------------- Get all applications ----------------
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // ---------------- Get applications by student ----------------
    public List<Application> getApplicationsByStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return applicationRepository.findByStudent(student);
    }

    // ---------------- Get applications by job ----------------
    public List<Application> getApplicationsByJob(Long jobPostId) {
        JobPost jobPost = jobPostRepository.findById(jobPostId)
                .orElseThrow(() -> new RuntimeException("Job post not found"));
        return applicationRepository.findByJobPost(jobPost);
    }

    // ---------------- Update status ----------------
    public Application updateStatus(Long applicationId, Status status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    // ---------------- Dedicated methods for enums ----------------
    public Application acceptApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(Status.ACCEPTED);
        return applicationRepository.save(application);
    }

    public Application rejectApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(Status.REJECTED);
        return applicationRepository.save(application);
    }

    public Application closeApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(Status.CLOSED);
        return applicationRepository.save(application);
    }

}
