package com.example.repository;

import com.example.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    @NonNull
    Recruiter findByEmail(@NonNull String email);

    @NonNull
    Optional<Recruiter> findById(@NonNull Long id);
}
