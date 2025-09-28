package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.example.enums.PaidStatus;

@Entity
@Table(name = "payments")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaidStatus status = PaidStatus.PENDING;
    private String externalPaymentId;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Payment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaidStatus getStatus() {
        return status;
    }

    public void setStatus(PaidStatus status) {
        this.status = status;
    }

    public String getExternalPaymentId() {
        return externalPaymentId;
    }

    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
