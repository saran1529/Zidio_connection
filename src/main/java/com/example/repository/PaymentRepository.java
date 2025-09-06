package com.example.repository;

import com.example.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused") // Suppress warning about unused interface
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
