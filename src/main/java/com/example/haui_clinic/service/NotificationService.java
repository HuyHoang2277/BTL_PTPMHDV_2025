package com.example.haui_clinic.service;

import com.example.haui_clinic.entity.Notification;
import com.example.haui_clinic.entity.User;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Notification save(Notification notification);
    Optional<Notification> findById(Integer id);
    List<Notification> findByUser(User user);
    List<Notification> findUnreadByUser(User user);
    List<Notification> findAll();
    void deleteById(Integer id);
}
