package com.example.controller;

import com.example.DTO.PaymentDTO;
import com.example.service.UserPaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user_subscription_status")
public class UserPaymentStatusController
{
    @Autowired
    private UserPaymentStatusService userPaymentStatusService;


    @PostMapping
    public ResponseEntity<PaymentDTO>assign(@RequestBody PaymentDTO dto ){
        return ResponseEntity.ok(userPaymentStatusService.assignSubscriptionPlan(dto));
    }

    @GetMapping("{userId}")
    public ResponseEntity<Optional<PaymentDTO>> getStatus(@PathVariable Long userId){
        return ResponseEntity.ok(userPaymentStatusService.getStatusByUserId(userId));
    }


}
