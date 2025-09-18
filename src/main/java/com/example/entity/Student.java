package com.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    private String phone;

    @Column(nullable = false)
    private String password = "";

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Student() {}
    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() {return password;}public void setPassword(String password) {this.password = password;}
    public LocalDateTime getCreatedAt() { return createdAt; }public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
}
