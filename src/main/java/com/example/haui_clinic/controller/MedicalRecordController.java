package com.example.haui_clinic.controller;

import com.example.haui_clinic.entity.MedicalRecord;
import com.example.haui_clinic.repository.MedicalRecordRepository;
import com.example.haui_clinic.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordRepository recordRepository;

    // 🟢 Bác sĩ thêm hồ sơ bệnh án cho lịch hẹn
    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public MedicalRecord addRecord(@RequestBody MedicalRecord record) {
        return recordRepository.save(record);
    }

    // 🟢 Bác sĩ cập nhật hồ sơ bệnh án
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    public MedicalRecord updateRecord(@PathVariable Integer id, @RequestBody MedicalRecord record) {
        MedicalRecord existing = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
        existing.setDiagnosis(record.getDiagnosis());
        existing.setPrescription(record.getPrescription());
        existing.setNextAppointmentNote(record.getNextAppointmentNote());
        return recordRepository.save(existing);
    }

    // 🟢 Bệnh nhân xem hồ sơ bệnh án của mình
    @GetMapping("/my")
    @PreAuthorize("hasRole('PATIENT')")
    public List<MedicalRecord> getMyRecords(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return recordRepository.findByAppointment_Patient_UserId(currentUser.getUser().getUserId());
    }

    // 🟢 Admin xem tất cả hồ sơ bệnh án
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<MedicalRecord> getAllRecords() {
        return recordRepository.findAll();
    }
}
