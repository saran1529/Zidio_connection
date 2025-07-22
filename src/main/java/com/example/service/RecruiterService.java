package com.example.service;

import com.example.DTO.RecruiterDTO;
import com.example.entity.Recruiter;
import com.example.repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterService
{
    @Autowired
    private RecruiterRepository recruiterRepository;

    public RecruiterDTO createRecruiter(RecruiterDTO dto) {
        Recruiter recruiter = new Recruiter(
                dto.id,
                dto.name,
                dto.email,
                dto.phone,
                dto.companyName,
                dto.companyDescription,
                dto.companyWebsite
        );
        recruiter = recruiterRepository.save(recruiter);
//        dto.setId(recruiter.getId());
        return dto;
    }

    public RecruiterDTO getRecruiterByEmail(String email) {
        Recruiter recruiter = recruiterRepository.findByEmail(email);
        if(recruiter == null) return null;
        return new RecruiterDTO(
                recruiter.getId(),
                recruiter.getName(),
                recruiter.getEmail(),
                recruiter.getPhone(),
                recruiter.getCompanyName(),
                recruiter.getCompanyDescription(),
                recruiter.getCompanyWebsite());
    }

    public RecruiterDTO getRecruiterById(Long id) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(id);
        if(optionalRecruiter.isEmpty()) return  null;
        Recruiter recruiter = optionalRecruiter.get();

        return new RecruiterDTO(
                recruiter.getId(),
                recruiter.getName(),
                recruiter.getEmail(),
                recruiter.getPhone(),
                recruiter.getCompanyName(),
                recruiter.getCompanyDescription(),
                recruiter.getCompanyWebsite());
    }
}