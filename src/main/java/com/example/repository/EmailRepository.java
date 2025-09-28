package com.example.repository;

import com.example.entity.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailLog, Long> {
}
