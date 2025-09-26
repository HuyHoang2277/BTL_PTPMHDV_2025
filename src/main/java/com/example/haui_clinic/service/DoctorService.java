package com.example.haui_clinic.service;

import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.entity.Specialty;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor save(Doctor doctor);
    Optional<Doctor> findById(Integer id);
    List<Doctor> findAll();
    List<Doctor> findBySpecialty(Specialty specialty);
    List<Doctor> findAvailableDoctors();
    void deleteById(Integer id);
}
