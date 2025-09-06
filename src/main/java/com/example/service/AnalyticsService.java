package com.example.service;

import com.example.DTO.AnalyticalResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    public AnalyticalResponseDTO collectData() {
        AnalyticalResponseDTO dto = new AnalyticalResponseDTO();
        // populate dto with analytics data
        return dto;
    }

    public List<AnalyticalResponseDTO> getBasicMetrics() {
        List<AnalyticalResponseDTO> list = new ArrayList<>();

        // First DTO
        AnalyticalResponseDTO dto1 = new AnalyticalResponseDTO();
        dto1.setStudents(100L);
        dto1.setRecruiters(50L);
        dto1.setJobPosts(20L);
        dto1.setAuth(10L);
        dto1.setApplications(30L);
        dto1.setAdmins(5L);
        dto1.setFileUpload("uploaded");
        dto1.setEmail("metrics@test.com");

        list.add(dto1);

        // Second DTO
        AnalyticalResponseDTO dto2 = new AnalyticalResponseDTO();
        dto2.setStudents(200L);
        dto2.setRecruiters(80L);
        dto2.setJobPosts(40L);
        dto2.setAuth(15L);
        dto2.setApplications(60L);
        dto2.setAdmins(10L);
        dto2.setFileUpload("uploaded_again");
        dto2.setEmail("report@test.com");

        list.add(dto2);

        return list;
    }
}
