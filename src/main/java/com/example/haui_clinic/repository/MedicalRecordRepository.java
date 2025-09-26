package com.example.haui_clinic.repository;

import com.example.haui_clinic.entity.MedicalRecord;
import com.example.haui_clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
    Optional<MedicalRecord> findByAppointment(Appointment appointment);
    List<MedicalRecord> findByAppointment_Patient_UserId(Integer userId);
}
