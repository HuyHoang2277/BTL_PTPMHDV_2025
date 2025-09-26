package com.example.haui_clinic.repository;

import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.entity.Specialty;
import com.example.haui_clinic.entity.User; // 🟢 thêm dòng này

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findBySpecialty(Specialty specialty);
    List<Doctor> findByIsAvailableTrue();
    Optional<Doctor> findByUser(User user);
}