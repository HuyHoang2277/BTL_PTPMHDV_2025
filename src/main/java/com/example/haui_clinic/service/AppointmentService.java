package com.example.haui_clinic.service;

import com.example.haui_clinic.entity.Appointment;
import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    Optional<Appointment> findById(Integer id);
    List<Appointment> findByPatient(User patient);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date);
    List<Appointment> findAll();
    void deleteById(Integer id);
}
