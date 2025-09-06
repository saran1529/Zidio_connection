package com.example.DTO;

public class RegisterRequest {
    private String name;
    private String email; // must match exactly
    private String password;
    private String role;

    public String getName() { return name; }
    public String getEmail() { return email; }  // ✅ must exist
    public String getPassword() { return password; }
    public String getRole() { return role; }    // ✅ must exist

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}
