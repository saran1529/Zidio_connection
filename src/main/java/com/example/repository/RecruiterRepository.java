package com.example.repository;

import com.example.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long>
{
    Recruiter findByEmail(String email);
    Optional<Recruiter> findById(Long id);
}
