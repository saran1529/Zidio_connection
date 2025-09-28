package com.example.controller;

import com.example.DTO.SubscriptionPlanDTO;
import com.example.service.UserPaymentStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user_subscription_status")
public class UserPaymentStatusController {

    private final UserPaymentStatusService userPaymentStatusService;

    public UserPaymentStatusController(UserPaymentStatusService userPaymentStatusService) {
        this.userPaymentStatusService = userPaymentStatusService;
    }

    @PostMapping
    public ResponseEntity<SubscriptionPlanDTO> assign(@RequestBody SubscriptionPlanDTO dto) {
        SubscriptionPlanDTO result = userPaymentStatusService.assignSubscriptionPlan(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<SubscriptionPlanDTO>> getStatus(@PathVariable Long userId) {
        Optional<SubscriptionPlanDTO> result = userPaymentStatusService.getStatusByUserId(userId);
        return ResponseEntity.ok(result);
    }
}
