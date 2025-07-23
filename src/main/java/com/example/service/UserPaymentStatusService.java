package com.example.service;

import com.example.DTO.PaymentDTO;
import com.example.entity.UserPaymentStatus;
import com.example.repository.UserPaymentStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPaymentStatusService {

    private final UserPaymentStatusRepository userPaymentStatusRepository;

    // ✅ Constructor injection to fix the warning and follow best practice
    public UserPaymentStatusService(UserPaymentStatusRepository userPaymentStatusRepository) {
        this.userPaymentStatusRepository = userPaymentStatusRepository;
    }

    public PaymentDTO assignSubscriptionPlan(PaymentDTO dto) {
        UserPaymentStatus paymentStatus = new UserPaymentStatus();
        paymentStatus.setUserId(dto.id);
        paymentStatus.setPlanId(dto.planId);
        paymentStatus.setSubscriptionStart(dto.subscriptionStart);
        paymentStatus.setSubscriptionEnd(dto.subscriptionEnd);
        paymentStatus.setStatus(dto.status);

        UserPaymentStatus saved = userPaymentStatusRepository.save(paymentStatus);

        // ✅ Update DTO with saved data
        dto.id = saved.getId();
        dto.subscriptionStart = saved.getSubscriptionStart();
        dto.subscriptionEnd = saved.getSubscriptionEnd();
        dto.status = saved.getStatus();
        return dto;
    }

    public Optional<PaymentDTO> getStatusByUserId(Long id) {
        return userPaymentStatusRepository.findByUserId(id).map(status -> {
            PaymentDTO dto = new PaymentDTO();
            dto.id = status.getId();
            dto.planId = status.getPlanId();
            dto.userId = status.getUserId();
            dto.subscriptionStart = status.getSubscriptionStart();
            dto.subscriptionEnd = status.getSubscriptionEnd();
            dto.status = status.getStatus();
            return dto;
        });
    }
}
