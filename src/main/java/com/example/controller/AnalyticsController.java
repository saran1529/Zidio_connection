package com.example.controller;

import com.example.DTO.AnalyticalResponseDTO;
import com.example.service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController
{
    @Autowired
    private AnalyticService analyticService;

    @GetMapping("/summery")
    public ResponseEntity<AnalyticalResponseDTO> getSummery() {
        return ResponseEntity.ok(analyticService.collectData());
    }
}
