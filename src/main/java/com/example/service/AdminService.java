package com.example.service;

import com.example.DTO.AdminUserDTO;
import com.example.enums.Role;
import com.example.entity.AdminUser;
import com.example.repository.SystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final SystemUserRepository systemUserRepository;

    // ✅ Constructor injection (recommended and warning-free)
    public AdminService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public List<AdminUserDTO> getAllUsers() {
        return systemUserRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void blockUser(Long id) {
        AdminUser user = systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
        user.setIsActive(false);
        systemUserRepository.save(user);
    }

    public void unBlockUser(Long id) {
        AdminUser user = systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
        user.setIsActive(true);
        systemUserRepository.save(user);
    }

    public List<AdminUserDTO> findByRole(Role role) {
        return systemUserRepository.findByRole(role)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AdminUserDTO updateStatus(String email, boolean isActive) {
        AdminUser user = systemUserRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("user not found");
        }

        user.setIsActive(isActive);
        systemUserRepository.save(user);
        return toDTO(user);
    }

    private AdminUserDTO toDTO(AdminUser user) {
        return new AdminUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getIsActive()
        );
    }
}
