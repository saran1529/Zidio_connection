package com.example.controller;

import com.example.DTO.AnalyticalResponseDTO;
import com.example.service.AnalyticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticService analyticService;

    // ✅ Constructor injection instead of field injection
    public AnalyticsController(AnalyticService analyticService) {
        this.analyticService = analyticService;
    }

    // ✅ Consider renaming endpoint from /summery → /summary for spelling correction
    @GetMapping("/summary")
    public ResponseEntity<AnalyticalResponseDTO> getSummary() {
        return ResponseEntity.ok(analyticService.collectData());
    }
}
