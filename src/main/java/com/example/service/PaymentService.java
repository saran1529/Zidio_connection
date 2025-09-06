package com.example.service;

import com.example.DTO.PaymentDTO;
import com.example.enums.PaidStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final List<PaymentDTO> payments = new ArrayList<>();

    public PaymentDTO makePayment(PaymentDTO dto) {
        dto.id = (long) (payments.size() + 1);
        dto.subscriptionStart = LocalDate.now();
        dto.subscriptionEnd = dto.subscriptionStart.plusDays(30);
        dto.status = PaidStatus.PAID;
        payments.add(dto);
        return dto;
    }

    public List<PaymentDTO> getAllPayments() {
        return payments;
    }
}
