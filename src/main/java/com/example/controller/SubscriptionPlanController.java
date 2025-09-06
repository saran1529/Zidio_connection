package com.example.controller;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.entity.SubscriptionPlan;
import com.example.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing subscription plans.
 */
@RestController
@RequestMapping("/api/subscription")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @Autowired
    public SubscriptionPlanController(SubscriptionPlanService subscriptionPlanService) {
        this.subscriptionPlanService = subscriptionPlanService;
    }

    /**
     * ✅ Get all subscription plans
     */
    @GetMapping
    public ResponseEntity<List<SubscriptionPlanDTO>> getAll() {
        List<SubscriptionPlanDTO> plans = subscriptionPlanService.list().stream()
                .map(plan -> {
                    SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
                    dto.setId(plan.getId());
                    dto.setPlanName(plan.getName());
                    dto.setPrice(plan.getPrice());
                    dto.setDuration(plan.getDurationDays());
                    // map other fields if needed
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(plans);
    }

    /**
     * ✅ Create a new subscription plan
     */
    @PostMapping
    public ResponseEntity<SubscriptionPlanDTO> create(@RequestBody SubscriptionPlanDTO dto) {
        SubscriptionPlan entity = new SubscriptionPlan();
        entity.setName(dto.getPlanName());
        entity.setPrice(dto.getPrice());
        entity.setDurationDays(dto.getDuration());
        // map other fields if needed

        SubscriptionPlan saved = subscriptionPlanService.create(entity);

        SubscriptionPlanDTO createdDto = new SubscriptionPlanDTO();
        createdDto.setId(saved.getId());
        createdDto.setPlanName(saved.getName());
        createdDto.setPrice(saved.getPrice());
        createdDto.setDuration(saved.getDurationDays());
        // map other fields if needed

        return ResponseEntity.ok(createdDto);
    }
}
