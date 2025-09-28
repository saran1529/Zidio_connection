package com.example.service;

import com.example.DTO.RecruiterDTO;
import com.example.entity.Recruiter;
import com.example.repository.RecruiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterService(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    /** Create a new recruiter using DTO */
    public RecruiterDTO createRecruiter(RecruiterDTO dto) {
        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyName(dto.getCompanyName());
        recruiter.setContactEmail(dto.getContactEmail());
        recruiter.setRecruiterName(dto.getRecruiterName());
        recruiter.setPhone(dto.getPhone());
        recruiter.setAddress(dto.getAddress());

        Recruiter saved = recruiterRepository.save(recruiter);

        return convertToDTO(saved);
    }

    // Get recruiter by ID
    public Optional<RecruiterDTO> getRecruiterById(Long id) {
        return recruiterRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Get recruiter by email (useful for login/search)
    public Optional<RecruiterDTO> getRecruiterByEmail(String email) {
        return recruiterRepository.findByContactEmail(email).map(this::convertToDTO);
    }

    // Get all recruiters
    public List<RecruiterDTO> getAllRecruiters() {
        return recruiterRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /** Update recruiter by ID using DTO */
    public RecruiterDTO updateRecruiter(Long id, RecruiterDTO dto) {
        Recruiter updated = recruiterRepository.findById(id)
                .map(existing -> {
                    existing.setCompanyName(dto.getCompanyName());
                    existing.setContactEmail(dto.getContactEmail());
                    existing.setRecruiterName(dto.getRecruiterName());
                    existing.setPhone(dto.getPhone());
                    existing.setAddress(dto.getAddress());
                    return recruiterRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Recruiter not found with id " + id));

        return convertToDTO(updated);
    }

    // Delete recruiter
    public void deleteRecruiter(Long id) {
        recruiterRepository.deleteById(id);
    }

    /** Convert entity to DTO */
    private RecruiterDTO convertToDTO(Recruiter recruiter) {
        RecruiterDTO dto = new RecruiterDTO();
        dto.setId(recruiter.getId());
        dto.setCompanyName(recruiter.getCompanyName());
        dto.setContactEmail(recruiter.getContactEmail());
        dto.setRecruiterName(recruiter.getRecruiterName());
        dto.setPhone(recruiter.getPhone());
        dto.setAddress(recruiter.getAddress());
        return dto;
    }
}
