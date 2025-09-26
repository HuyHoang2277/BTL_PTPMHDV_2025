package com.example.haui_clinic.repository;

import com.example.haui_clinic.entity.DoctorSchedule;
import com.example.haui_clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Integer> {
    List<DoctorSchedule> findByDoctor(Doctor doctor);
    List<DoctorSchedule> findByDoctorAndDayOfWeek(Doctor doctor, Integer dayOfWeek);
}
