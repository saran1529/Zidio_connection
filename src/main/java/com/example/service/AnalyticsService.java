package com.example.service;

import com.example.DTO.AnalyticalResponseDTO;
import com.example.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    private final StudentRepository studentRepo;
    private final RecruiterRepository recruiterRepo;
    private final JobPostRepository jobPostRepo;
    private final AppUserRepository authRepo;        // added
    private final ApplicationRepository applicationRepo; // added
    private final AdminUserRepository adminRepo;
    private final FileUploadRepository fileUploadRepo; // added
    private final EmailRepository emailRepo;           // added

    public AnalyticsService(StudentRepository studentRepo,
                            RecruiterRepository recruiterRepo,
                            JobPostRepository jobPostRepo,
                            AppUserRepository authRepo,
                            ApplicationRepository applicationRepo,
                            AdminUserRepository adminRepo,
                            FileUploadRepository fileUploadRepo, // added
                            EmailRepository emailRepo) {
        this.studentRepo = studentRepo;
        this.recruiterRepo = recruiterRepo;
        this.jobPostRepo = jobPostRepo;
        this.authRepo = authRepo;
        this.applicationRepo = applicationRepo;
        this.adminRepo = adminRepo;
        this.fileUploadRepo = fileUploadRepo; // added
        this.emailRepo = emailRepo;           // added
    }

    public AnalyticalResponseDTO collectData() {
        AnalyticalResponseDTO dto = new AnalyticalResponseDTO();

        dto.setStudents(studentRepo.count());         // changed from null → count()
        dto.setRecruiters(recruiterRepo.count());     // changed
        dto.setJobPosts(jobPostRepo.count());         // changed
        dto.setAuth(authRepo.count());                // changed
        dto.setApplications(applicationRepo.count()); // changed
        dto.setAdmins(adminRepo.count());             // changed

        dto.setFileUploads(fileUploadRepo.count()); // example placeholder, you can implement actual file upload counts
        dto.setEmails(emailRepo.count());      // example placeholder, you can implement actual email counts

        return dto;
    }
}
