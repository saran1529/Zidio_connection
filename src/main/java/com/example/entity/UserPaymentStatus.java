package com.example.entity;

import com.example.enums.PaidStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_payment_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPaymentStatus {

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
}
