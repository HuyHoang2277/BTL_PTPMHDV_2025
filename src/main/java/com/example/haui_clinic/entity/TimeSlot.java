package com.example.haui_clinic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "time_slots")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer slotId;

    private String startTime;

    private String endTime;

    private Integer durationMinutes = 30;

    private Boolean isActive = true;
}
