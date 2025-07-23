package com.example.service;

import com.example.DTO.AnalyticalResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AnalyticService {

    private final RestTemplate restTemplate;

    public AnalyticService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AnalyticalResponseDTO collectData() {
        Long students = restTemplate.getForObject(
                "http://student-service/internal/count", Long.class);
        Long recruiters = restTemplate.getForObject(
                "http://recruiters-service/internal/count", Long.class);
        Long jobPosts = restTemplate.getForObject(
                "http://jobPosts-service/internal/count", Long.class);
        Long auth = restTemplate.getForObject(
                "http://auth-service/internal/count", Long.class);
        Long applications = restTemplate.getForObject(
                "http://applications-service/internal/count", Long.class);
        Long admins = restTemplate.getForObject(
                "http://admins-service/internal/count", Long.class);
        String fileUpload = restTemplate.getForObject(
                "http://fileUpload-service/internal/count", String.class);
        String email = restTemplate.getForObject(
                "http://email-service/internal/count", String.class);

        return new AnalyticalResponseDTO(
                students,
                recruiters,
                jobPosts,
                auth,
                applications,
                admins,
                fileUpload,
                email
        );
    }
}
