package com.example.DTO;

import com.example.enums.PaidStatus;
import java.time.LocalDate;

public class PaymentDTO
{
    public Long id;
    public Long planId;
    public Long userId;
    public LocalDate subscriptionStart;
    public LocalDate subscriptionEnd;
    public PaidStatus status;

    public PaymentDTO() {}

    public PaymentDTO(
            Long id,
            Long planId,
            Long userId,
            LocalDate subscriptionStart,
            LocalDate subscriptionEnd,
            PaidStatus status) {
                    this.id = id;
                    this.planId = planId;
                    this.userId = userId;
                    this.subscriptionStart = subscriptionStart;
                    this.subscriptionEnd = subscriptionEnd;
                    this.status = status;
    }

}
