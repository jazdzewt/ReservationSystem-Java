package com.example.reservationsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "admins")
public class Admin {

    // getters and setters
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

}
