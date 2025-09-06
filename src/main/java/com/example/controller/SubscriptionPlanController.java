package com.example.controller;

import java.util.List;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    // ✅ Use constructor-based injection (recommended)
    @Autowired
    public SubscriptionPlanController(SubscriptionPlanService subscriptionPlanService) {
        this.subscriptionPlanService = subscriptionPlanService;
    }

    // ✅ This method returns all subscription plans
    @GetMapping
    public ResponseEntity<List<SubscriptionPlanDTO>> getAll() {
        return ResponseEntity.ok(subscriptionPlanService.getAllSubscriptionPlan());
    }

    // ✅ Use @RequestBody instead of @RequestParam for POSTed JSON body
    @PostMapping
    public ResponseEntity<SubscriptionPlanDTO> create(@RequestBody SubscriptionPlanDTO dto) {
        return ResponseEntity.ok(subscriptionPlanService.createSubscription(dto));
    }
}
