package com.example.haui_clinic.service.impl;

import com.example.haui_clinic.entity.Notification;
import com.example.haui_clinic.entity.User;
import com.example.haui_clinic.repository.NotificationRepository;
import com.example.haui_clinic.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> findById(Integer id) {
        return notificationRepository.findById(id);
    }

    @Override
    public List<Notification> findByUser(User user) {
        return notificationRepository.findByUser(user);
    }

    @Override
    public List<Notification> findUnreadByUser(User user) {
        return notificationRepository.findByUserAndIsRead(user, false);
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        notificationRepository.deleteById(id);
    }
}
