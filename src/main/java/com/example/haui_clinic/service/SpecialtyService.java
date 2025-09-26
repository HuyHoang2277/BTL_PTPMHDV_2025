package com.example.haui_clinic.service;

import com.example.haui_clinic.entity.Specialty;

import java.util.List;
import java.util.Optional;

public interface SpecialtyService {
    Specialty save(Specialty specialty);
    Optional<Specialty> findById(Integer id);
    List<Specialty> findAll();
    void deleteById(Integer id);
}
