package com.example.haui_clinic.service;

import com.example.haui_clinic.entity.TimeSlot;

import java.util.List;
import java.util.Optional;

public interface TimeSlotService {
    TimeSlot save(TimeSlot timeSlot);
    Optional<TimeSlot> findById(Integer id);
    List<TimeSlot> findAll();
    void deleteById(Integer id);
}
