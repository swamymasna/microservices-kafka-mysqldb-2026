package com.kes.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kes.notificationservice.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
