package com.example.entity;

import com.example.enums.PaidStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_payment_status")
public class UserPaymentStatus
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long planId;

    @Column(name = "user_id")
    private Long userId;
    private LocalDate subscriptionStart;
    private LocalDate subscriptionEnd;

    @Enumerated(EnumType.STRING)
    private PaidStatus status;

    public UserPaymentStatus(){}

    public UserPaymentStatus(
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getSubscriptionStart() {
        return subscriptionStart;
    }

    public void setSubscriptionStart(LocalDate subscriptionStart) {
        this.subscriptionStart = subscriptionStart;
    }

    public LocalDate getSubscriptionEnd() {
        return subscriptionEnd;
    }

    public void setSubscriptionEnd(LocalDate subscriptionEnd) {
        this.subscriptionEnd = subscriptionEnd;
    }

    public PaidStatus getStatus() {
        return status;
    }

    public void setStatus(PaidStatus status) {
        this.status = status;
    }
}
