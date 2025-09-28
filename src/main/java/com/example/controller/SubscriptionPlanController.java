package com.example.controller;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.entity.SubscriptionPlan;
import com.example.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @Autowired
    public SubscriptionPlanController(SubscriptionPlanService subscriptionPlanService) {
        this.subscriptionPlanService = subscriptionPlanService;
    }

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

    @PostMapping
    public ResponseEntity<SubscriptionPlanDTO> create(@RequestBody SubscriptionPlanDTO dto) {
        SubscriptionPlan entity = new SubscriptionPlan();
        entity.setName(dto.getPlanName());
        entity.setPrice(dto.getPrice());
        entity.setDurationDays(dto.getDuration());

        SubscriptionPlan saved = subscriptionPlanService.create(entity);

        SubscriptionPlanDTO createdDto = new SubscriptionPlanDTO();
        createdDto.setId(saved.getId());
        createdDto.setPlanName(saved.getName());
        createdDto.setPrice(saved.getPrice());
        createdDto.setDuration(saved.getDurationDays());

        return ResponseEntity.ok(createdDto);
    }
}
