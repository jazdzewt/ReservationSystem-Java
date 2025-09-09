package com.example.reservationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @PostMapping("/reserve")
    public String reserve(@RequestParam("date") String date, Model model) {
        model.addAttribute("msg", "Rezerwacja przyjęta na " + date);
        return "confirmation.html";
    }
}
