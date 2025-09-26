package com.example.haui_clinic.service.impl;

import com.example.haui_clinic.entity.Specialty;
import com.example.haui_clinic.repository.SpecialtyRepository;
import com.example.haui_clinic.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Optional<Specialty> findById(Integer id) {
        return specialtyRepository.findById(id);
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        specialtyRepository.deleteById(id);
    }
}
