package com.example.service;

import com.example.entity.Recruiter;
import com.example.repository.RecruiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    // ✅ Create a new recruiter
    public Recruiter createRecruiter(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    // ✅ Get recruiter by ID
    public Optional<Recruiter> getRecruiterById(Long id) {
        return recruiterRepository.findById(id);
    }

    // ✅ Get recruiter by email (useful for login/search)
    public Optional<Recruiter> getRecruiterByEmail(String email) {
        return recruiterRepository.findByContactEmail(email);
    }

    // ✅ Get all recruiters
    public List<Recruiter> getAllRecruiters() {
        return recruiterRepository.findAll();
    }

    // ✅ Update recruiter
    public Recruiter updateRecruiter(Long id, Recruiter updatedRecruiter) {
        return recruiterRepository.findById(id)
                .map(existing -> {
                    existing.setCompanyName(updatedRecruiter.getCompanyName());
                    existing.setContactEmail(updatedRecruiter.getContactEmail());
                    existing.setRecruiterName(updatedRecruiter.getRecruiterName());
                    existing.setPhone(updatedRecruiter.getPhone());
                    existing.setAddress(updatedRecruiter.getAddress());
                    return recruiterRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Recruiter not found with id " + id));
    }

    // ✅ Delete recruiter
    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }
}
