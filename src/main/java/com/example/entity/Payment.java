package com.example.entity;

import com.example.enums.PaymentStatus;
import com.example.enums.PaymentType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    private Long userId;
    private Long planId;

    @Column(name = "transaction_id")
    private String transactionId;
    private BigDecimal amount;
    private String currency;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private LocalDateTime paymentDate;


    public Payment() {}

    public Payment(Long id,Long userId,Long planId,BigDecimal amount,String currency,PaymentStatus paymentStatus,String transactionId,PaymentType paymentType,LocalDateTime paymentDate) {
        this.id=id;
        this.userId=userId;
        this.planId=planId;
        this.amount=amount;
        this.currency=currency;
        this.paymentStatus=paymentStatus;
        this.transactionId=transactionId;
        this.paymentType=paymentType;
        this.paymentDate=paymentDate;
    }

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

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
