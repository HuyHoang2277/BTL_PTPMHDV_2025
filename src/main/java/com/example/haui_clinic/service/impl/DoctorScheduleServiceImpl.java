package com.example.haui_clinic.service.impl;

import com.example.haui_clinic.entity.DoctorSchedule;
import com.example.haui_clinic.entity.Doctor;
import com.example.haui_clinic.repository.DoctorScheduleRepository;
import com.example.haui_clinic.service.DoctorScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;

    @Override
    public DoctorSchedule save(DoctorSchedule schedule) {
        return doctorScheduleRepository.save(schedule);
    }

    @Override
    public Optional<DoctorSchedule> findById(Integer id) {
        return doctorScheduleRepository.findById(id);
    }

    @Override
    public List<DoctorSchedule> findByDoctor(Doctor doctor) {
        return doctorScheduleRepository.findByDoctor(doctor);
    }

    @Override
    public List<DoctorSchedule> findByDoctorAndDayOfWeek(Doctor doctor, Integer dayOfWeek) {
        return doctorScheduleRepository.findByDoctorAndDayOfWeek(doctor, dayOfWeek);
    }

    @Override
    public List<DoctorSchedule> findAll() {
        return doctorScheduleRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        doctorScheduleRepository.deleteById(id);
    }
}
