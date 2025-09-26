package com.example.haui_clinic.service;

import com.example.haui_clinic.entity.DoctorSchedule;
import com.example.haui_clinic.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorScheduleService {
    DoctorSchedule save(DoctorSchedule schedule);
    Optional<DoctorSchedule> findById(Integer id);
    List<DoctorSchedule> findByDoctor(Doctor doctor);
    List<DoctorSchedule> findByDoctorAndDayOfWeek(Doctor doctor, Integer dayOfWeek);
    List<DoctorSchedule> findAll();
    void deleteById(Integer id);
}
