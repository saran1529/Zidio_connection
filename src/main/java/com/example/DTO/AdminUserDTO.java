package com.example.DTO;

public class AdminUserDTO {

    private Long id;
    private String username;
    private String name;
    private String password; // ✅ added

    public AdminUserDTO() {}

    public AdminUserDTO(Long id,
                        String username,
                        String name,
                        String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public AdminUserDTO(Long id,
                        String username,
                        String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
