package org.graduatetrackingsystem.graduatetracking.services;

import org.graduatetrackingsystem.graduatetracking.dtos.NotificationDto;
import org.graduatetrackingsystem.graduatetracking.dtos.NotificationRequest;

import java.util.List;

public interface NotifitaionService {
    List<NotificationDto> getAllNotification();
    NotificationDto createNotification(NotificationDto notificationDto);

    NotificationDto updateNotification(Long id, NotificationDto notificationDto);

    void deletenotification(Long id);
}
