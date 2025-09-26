package com.example.haui_clinic.service.impl;

import com.example.haui_clinic.entity.MedicalRecord;
import com.example.haui_clinic.entity.Appointment;
import com.example.haui_clinic.repository.MedicalRecordRepository;
import com.example.haui_clinic.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord save(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }

    @Override
    public Optional<MedicalRecord> findById(Integer id) {
        return medicalRecordRepository.findById(id);
    }

    @Override
    public Optional<MedicalRecord> findByAppointment(Appointment appointment) {
        return medicalRecordRepository.findByAppointment(appointment);
    }

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        medicalRecordRepository.deleteById(id);
    }
}
