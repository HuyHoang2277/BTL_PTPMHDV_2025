package com.example.haui_clinic.controller;

import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.entity.Specialty;
import com.example.haui_clinic.entity.User;
import com.example.haui_clinic.repository.DoctorRepository;
import com.example.haui_clinic.repository.SpecialtyRepository;
import com.example.haui_clinic.repository.UserRepository;
import com.example.haui_clinic.security.CustomUserDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;

    // 🟢 Admin tạo Doctor profile cho 1 user có role = DOCTOR
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Doctor createDoctorProfile(@RequestBody CreateDoctorRequest req) {
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!"DOCTOR".equals(user.getRole().name())) {
            throw new RuntimeException("User is not a doctor");
        }

        Specialty specialty = specialtyRepository.findById(req.getSpecialtyId())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setSpecialty(specialty);
        doctor.setExperienceYears(req.getExperienceYears());
        doctor.setIsAvailable(true);

        return doctorRepository.save(doctor);
    }
    // 🟢 Bệnh nhân / admin xem danh sách bác sĩ
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // 🟢 Xem chi tiết 1 bác sĩ theo doctorId
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    public Doctor getDoctorById(@PathVariable Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    // 🟢 Bác sĩ xem profile của chính mình (dựa vào token)
    @GetMapping("/me")
    @PreAuthorize("hasRole('DOCTOR')")
    public Doctor getMyProfile(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return doctorRepository.findByUser(currentUser.getUser())
                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));
    }
}

@Data
class CreateDoctorRequest {
    private Integer userId;
    private Integer specialtyId;
    private Integer experienceYears;
}
