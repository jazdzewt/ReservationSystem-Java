package com.example.reservationsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // will be stored as hashed string (BCrypt)

    private String role = "ROLE_ADMIN";

    public Admin() {}

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
