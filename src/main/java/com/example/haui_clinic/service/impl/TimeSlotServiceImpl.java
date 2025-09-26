package com.example.haui_clinic.service.impl;

import com.example.haui_clinic.entity.TimeSlot;
import com.example.haui_clinic.repository.TimeSlotRepository;
import com.example.haui_clinic.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Override
    public TimeSlot save(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    @Override
    public Optional<TimeSlot> findById(Integer id) {
        return timeSlotRepository.findById(id);
    }

    @Override
    public List<TimeSlot> findAll() {
        return timeSlotRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        timeSlotRepository.deleteById(id);
    }
}
