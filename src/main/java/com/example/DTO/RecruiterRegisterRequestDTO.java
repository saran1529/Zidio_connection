package com.example.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecruiterRegisterRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email formate")
    private String email;  // Login email

    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be at least 6 characters long")
    private String password;       // Login password

    @NotBlank(message = "Company name is required")
    private String companyName;    // Company name

    @NotBlank(message = "Phone number is required")
    private String phone;     // contact number

    public RecruiterRegisterRequestDTO() {}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
