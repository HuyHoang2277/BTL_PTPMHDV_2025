package com.example.haui_clinic.controller;

import com.example.haui_clinic.entity.User;
import com.example.haui_clinic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final UserRepository userRepository;

    // Xem profile bệnh nhân
    @GetMapping("/profile")
    public User getPatientProfile(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // Giả sử sau này bệnh nhân có thể đặt lịch khám
    @PostMapping("/appointments")
    public String bookAppointment(@RequestParam String doctorName) {
        return "Appointment booked with doctor " + doctorName;
    }
}
