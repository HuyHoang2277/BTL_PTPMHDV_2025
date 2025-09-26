package com.example.haui_clinic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctor_schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private Integer dayOfWeek; // 1-7 (Thứ 2 -> CN)

    private String startTime;  // TIME -> String (hoặc LocalTime)

    private String endTime;

    private Integer maxPatients = 10;

    private Boolean isActive = true;
}
