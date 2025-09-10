package com.example.reservationsystem.service;

import com.example.reservationsystem.model.Admin;
import com.example.reservationsystem.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void registerAdmin(String username, String rawPassword) {
        String hashed = passwordEncoder.encode(rawPassword);
        Admin admin = new Admin(username, hashed);
        adminRepository.save(admin);
    }
}
