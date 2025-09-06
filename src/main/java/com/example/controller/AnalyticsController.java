package com.example.controller;

import com.example.DTO.AnalyticalResponseDTO;
import com.example.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public ResponseEntity<AnalyticalResponseDTO> getSummary() {
        AnalyticalResponseDTO summary = analyticsService.collectData();
        return ResponseEntity.ok(summary);
    }
}
