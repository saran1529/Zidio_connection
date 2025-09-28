package com.example.DTO;

public class AuthResponse {
    private String name;
    private String username;
    private String role;
    private String message;
    private String token;

    public AuthResponse() {}

    public AuthResponse(String name,
                        String username,
                        String role,
                        String message,
                        String token) {
        this.name = name;
        this.username = username;
        this.role = role;
        this.message = message;
        this.token = token;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return  token; }
    public void setToken(String token) { this.token = token; }
}
