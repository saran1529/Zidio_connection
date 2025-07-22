package com.example.service;

import java.util.List;
import com.example.DTO.SubscriptionPlanDTO;
import java.util.stream.Collectors;

import com.example.entity.SubscriptionPlan;
import com.example.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionPlanService
{
    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;


    public List<SubscriptionPlanDTO> getAllSubscriptionPlan(){
        return subscriptionPlanRepository.findAll().stream().map(sub ->{
            SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
            dto.id=sub.getId();
            dto.name=sub.getName();
            dto.price=sub.getPrice();
            dto.description=sub.getDescription();
            dto.durationInDays=sub.getDurationInDays();
            return dto;
        }).collect(Collectors.toList());
    }

    public SubscriptionPlanDTO createSubscription(SubscriptionPlanDTO dto) {
        SubscriptionPlan subcription= new SubscriptionPlan();
        subcription.setName(dto.name);
        subcription.setId(dto.id);
        subcription.setPrice(dto.price);
        subcription.setDescription(dto.description);
        subcription.setDurationInDays(dto.durationInDays);

        SubscriptionPlan saved = subscriptionPlanRepository.save(subcription);
        dto.id= saved.getId();
        return dto;
    }


}
