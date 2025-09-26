package com.example.haui_clinic.controller;

import com.example.haui_clinic.entity.Specialty;
import com.example.haui_clinic.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyRepository specialtyRepository;

    // 🟢 Admin thêm chuyên khoa
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Specialty createSpecialty(@RequestBody Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    // 🟢 Tất cả user (đã login) xem danh sách chuyên khoa
    @GetMapping
    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }
}
