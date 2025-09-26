package com.example.haui_clinic.repository;

import com.example.haui_clinic.entity.Notification;
import com.example.haui_clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUser(User user);
    List<Notification> findByUserAndIsRead(User user, Boolean isRead);
}
