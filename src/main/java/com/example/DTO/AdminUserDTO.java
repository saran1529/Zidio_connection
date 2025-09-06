package com.example.DTO;

import com.example.enums.Role;

public class AdminUserDTO
{
    public Long id;
    public  String name;
    public String email;
    public Role role;
    public boolean isActive;

    public AdminUserDTO(
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

}
