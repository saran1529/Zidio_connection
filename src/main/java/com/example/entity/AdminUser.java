package com.example.entity;

import com.example.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "systemUsers")
public class AdminUser
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private Role role;

    private boolean isActive;

    public AdminUser(
            Long id,
            String name,
            String email,
            Role role,
            boolean isActive) {
                        this.id = id;
                        this.name = name;
                        this.email = email;
                        this.role = role;
                        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
