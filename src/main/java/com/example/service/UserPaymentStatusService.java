package com.example.service;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.entity.UserPaymentStatus;
import com.example.repository.UserPaymentStatusRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserPaymentStatusService {

    private final UserPaymentStatusRepository repository;

    public UserPaymentStatusService(UserPaymentStatusRepository repository) {
        this.repository = repository;
    }

    /** Assign subscription plan */
    public SubscriptionPlanDTO assignSubscriptionPlan(SubscriptionPlanDTO dto) {
        UserPaymentStatus entity = new UserPaymentStatus();

        if (dto.getId() != null) {
            entity = repository.findById(dto.getId()).orElse(new UserPaymentStatus());
        }

        entity.setUserId(dto.getUserId());
        entity.setPlanName(dto.getPlanName());
        entity.setStartDate(LocalDate.now()); // Start today
        entity.setEndDate(calculateEndDate(dto.getPlanName(), entity.getStartDate())); // Auto endDate
        entity.setActive(true);

        UserPaymentStatus saved = repository.save(entity);
        return convertToDTO(saved);
    }

    /** Get subscription status by userId with auto-expiry check */
    public Optional<SubscriptionPlanDTO> getStatusByUserId(Long userId) {
        Optional<UserPaymentStatus> optional = repository.findByUserId(userId);

        if (optional.isPresent()) {
            UserPaymentStatus entity = optional.get();

            // Auto deactivate if expired
            if (LocalDate.now().isAfter(entity.getEndDate())) {
                entity.setActive(false);
                repository.save(entity);
            }

            return Optional.of(convertToDTO(entity));
        }
        return Optional.empty();
    }

    /** Convert entity → DTO */
    private SubscriptionPlanDTO convertToDTO(UserPaymentStatus entity) {
        SubscriptionPlanDTO dto = new SubscriptionPlanDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setPlanName(entity.getPlanName());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setActive(entity.isActive());

        return dto;
    }

    /** Auto calculate subscription end date based on planName */
    private LocalDate calculateEndDate(String planName, LocalDate startDate) {
        switch (planName.toUpperCase()) {
            case "BASIC":    return startDate.plusDays(30);
            case "STANDARD": return startDate.plusDays(90);
            case "PREMIUM":  return startDate.plusDays(365);
            default:         return startDate.plusDays(30); // fallback
        }
    }
}
