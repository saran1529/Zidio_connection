package com.example.service;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.entity.SubscriptionPlan;
import com.example.repository.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;

    // ✅ Constructor Injection (removes warning)
    public SubscriptionPlanService(SubscriptionPlanRepository subscriptionPlanRepository) {
        this.subscriptionPlanRepository = subscriptionPlanRepository;
    }

    public List<SubscriptionPlanDTO> getAllSubscriptionPlan() {
        return subscriptionPlanRepository.findAll()
                .stream()
                .map(sub -> {
                    SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
                    dto.setId(sub.getId());
                    dto.setName(sub.getName());
                    dto.setPrice(sub.getPrice());
                    dto.setDescription(sub.getDescription());
                    dto.setDurationInDays(sub.getDurationInDays());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public SubscriptionPlanDTO createSubscription(SubscriptionPlanDTO dto) {
        SubscriptionPlan subscription = new SubscriptionPlan();
        subscription.setId(dto.getId());
        subscription.setName(dto.getName());
        subscription.setPrice(dto.getPrice());
        subscription.setDescription(dto.getDescription());
        subscription.setDurationInDays(dto.getDurationInDays());

        SubscriptionPlan saved = subscriptionPlanRepository.save(subscription);

        dto.setId(saved.getId());
        return dto;
    }
}
