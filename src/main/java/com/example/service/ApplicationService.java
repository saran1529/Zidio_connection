package com.example.service;

import com.example.DTO.ApplicationDTO;
import com.example.enums.Status;
import com.example.entity.Application;
import com.example.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    // ✅ Constructor injection (removes warning and is preferred)
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationDTO apply(ApplicationDTO dto) {
        Application app = mapToEntity(dto);
        Application saved = applicationRepository.save(app);
        return mapToDTO(saved);
    }

    public List<ApplicationDTO> getApplicationByStudentId(Long studentId) {
        return applicationRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ApplicationDTO> getApplicationByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void updateStatus(Long id, Status status) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus(status);
        applicationRepository.save(app);
    }

    private ApplicationDTO mapToDTO(Application app) {
        return new ApplicationDTO(
                app.getId(),
                app.getStudentId(),
                app.getJobId(),
                app.getResumeURL(),
                app.getStatus(),
                app.getAppliedDate()
        );
    }

    private Application mapToEntity(ApplicationDTO dto) {
        return new Application(
                dto.getId(),
                dto.getStudentId(),
                dto.getJobId(),
                dto.getResumeURL(),
                dto.getStatus(),
                dto.getAppliedDate()
        );
    }
}
