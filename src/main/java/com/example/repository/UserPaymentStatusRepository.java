package com.example.repository;

import com.example.entity.UserPaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPaymentStatusRepository extends JpaRepository<UserPaymentStatus, Long> {
    Optional<UserPaymentStatus> findByUserId(Long userId);
}
