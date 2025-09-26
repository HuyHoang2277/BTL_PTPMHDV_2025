package com.example.haui_clinic.service;

import com.example.haui_clinic.entity.MedicalRecord;
import com.example.haui_clinic.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    MedicalRecord save(MedicalRecord record);
    Optional<MedicalRecord> findById(Integer id);
    Optional<MedicalRecord> findByAppointment(Appointment appointment);
    List<MedicalRecord> findAll();
    void deleteById(Integer id);
}
