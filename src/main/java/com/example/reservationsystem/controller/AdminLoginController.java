package com.example.reservationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLoginController {

    // Wyświetla formularz logowania (custom login page)
    @GetMapping("/login")
    public String loginForm(Model model) {
        return "admin-login"; // wskazuje na templates/admin-login.html
    }

    // Dashboard admina – tylko dostęp dla zalogowanych adminów
    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        // Spring Security automatycznie wie, kto jest zalogowany
        return "admin-dashboard"; // wskazuje na templates/admin-dashboard.html
    }
}
