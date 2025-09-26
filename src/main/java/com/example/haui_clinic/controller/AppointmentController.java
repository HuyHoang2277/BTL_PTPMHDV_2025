package com.example.haui_clinic.controller;

import com.example.haui_clinic.entity.Appointment;
import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.entity.User;
import com.example.haui_clinic.repository.AppointmentRepository;
import com.example.haui_clinic.repository.DoctorRepository;
import com.example.haui_clinic.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    // 🟢 Bệnh nhân đặt lịch hẹn
    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    public Appointment bookAppointment(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @RequestBody Appointment appointment
    ) {
        appointment.setPatient(currentUser.getUser()); // gán bệnh nhân hiện tại
        appointment.setStatus(Appointment.Status.PENDING); // Enum
        return appointmentRepository.save(appointment);
    }

    // 🟢 Bệnh nhân xem lịch hẹn của mình
    @GetMapping("/my")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Appointment> getMyAppointments(@AuthenticationPrincipal CustomUserDetails currentUser) {
        User patient = currentUser.getUser();
        return appointmentRepository.findByPatient(patient);
    }

    // 🟢 Bác sĩ xem lịch hẹn của mình
    @GetMapping("/doctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Appointment> getDoctorAppointments(@AuthenticationPrincipal CustomUserDetails currentUser) {
        Doctor doctor = doctorRepository.findByUser(currentUser.getUser())
                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));
        return appointmentRepository.findByDoctor(doctor);
    }

    // 🟢 Admin xem tất cả lịch hẹn
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // 🟢 Bệnh nhân hủy lịch hẹn
    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('PATIENT')")
    public Appointment cancelAppointment(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @PathVariable Integer id // ✅ để Integer cho khớp với repo
    ) {
        Appointment appt = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        if (!appt.getPatient().getUserId().equals(currentUser.getUser().getUserId())) {
            throw new RuntimeException("Not your appointment!");
        }
        appt.setStatus(Appointment.Status.CANCELLED); // ✅ enum
        return appointmentRepository.save(appt);
    }
}
