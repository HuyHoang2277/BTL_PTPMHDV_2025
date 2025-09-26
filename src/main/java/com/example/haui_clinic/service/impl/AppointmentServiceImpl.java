package com.example.haui_clinic.service.impl;

import com.example.haui_clinic.entity.Appointment;
import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.entity.User;
import com.example.haui_clinic.repository.AppointmentRepository;
import com.example.haui_clinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<Appointment> findById(Integer id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> findByPatient(User patient) {
        return appointmentRepository.findByPatient(patient);
    }

    @Override
    public List<Appointment> findByDoctor(Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    @Override
    public List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date) {
        return appointmentRepository.findByDoctorAndAppointmentDate(doctor, date);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        appointmentRepository.deleteById(id);
    }
}
