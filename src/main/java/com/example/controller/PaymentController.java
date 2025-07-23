package com.example.controller;

import com.example.DTO.PaymentDTO;
import com.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.makePayment(dto));
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAll() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }
}
