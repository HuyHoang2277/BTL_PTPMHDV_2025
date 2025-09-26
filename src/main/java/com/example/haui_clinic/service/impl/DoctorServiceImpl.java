package com.example.haui_clinic.service.impl;

import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.entity.Specialty;
import com.example.haui_clinic.repository.DoctorRepository;
import com.example.haui_clinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findBySpecialty(Specialty specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    @Override
    public List<Doctor> findAvailableDoctors() {
        return doctorRepository.findByIsAvailableTrue();
    }

    @Override
    public void deleteById(Integer id) {
        doctorRepository.deleteById(id);
    }
}
