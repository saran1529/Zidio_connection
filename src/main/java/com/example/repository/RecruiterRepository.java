package com.example.repository;

import com.example.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    Optional<Recruiter> findByContactEmail(String email);
}
