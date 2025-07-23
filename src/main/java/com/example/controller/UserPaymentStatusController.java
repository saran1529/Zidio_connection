package com.example.controller;

import com.example.DTO.PaymentDTO;
import com.example.service.UserPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user_subscription_status")
public class UserPaymentStatusController {

    private final UserPaymentStatusService userPaymentStatusService;

    // ✅ Constructor-based dependency injection (recommended)
    @Autowired
    public UserPaymentStatusController(UserPaymentStatusService userPaymentStatusService) {
        this.userPaymentStatusService = userPaymentStatusService;
    }

    // ✅ Assign subscription plan to user
    @PostMapping
    public ResponseEntity<PaymentDTO> assign(@RequestBody PaymentDTO dto) {
        PaymentDTO result = userPaymentStatusService.assignSubscriptionPlan(dto);
        return ResponseEntity.ok(result);
    }

    // ✅ Get subscription status by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<PaymentDTO>> getStatus(@PathVariable Long userId) {
        Optional<PaymentDTO> result = userPaymentStatusService.getStatusByUserId(userId);
        return ResponseEntity.ok(result);
    }
}
