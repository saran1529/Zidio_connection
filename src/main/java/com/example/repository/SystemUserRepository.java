package com.example.repository;

import com.example.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.enums.Role;

import java.util.List;

@Repository
public interface SystemUserRepository extends JpaRepository<AdminUser, Long>
{
    AdminUser findByEmail(String email);

    List<AdminUser> findByRole(Role role);
}
