package org.graduatetrackingsystem.graduatetracking.ServiceIpml;

import lombok.RequiredArgsConstructor;
import org.graduatetrackingsystem.graduatetracking.dtos.AdminDto;
import org.graduatetrackingsystem.graduatetracking.dtos.NotificationDto;
import org.graduatetrackingsystem.graduatetracking.dtos.NotificationRequest;
import org.graduatetrackingsystem.graduatetracking.dtos.StudentDto;
import org.graduatetrackingsystem.graduatetracking.entities.Notification;
import org.graduatetrackingsystem.graduatetracking.repositories.NotificationRepository;
import org.graduatetrackingsystem.graduatetracking.services.NotifitaionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotifitionServiceImpl implements NotifitaionService {
    @Autowired
    private final NotificationRepository notificationRepository;
    @Override
    public List<NotificationDto> getAllNotification() {
            return notificationRepository.findAll().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }


    @Override
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = toEntity(notificationDto);
        return toDto(notificationRepository.save(notification));
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
        Notification notification = toEntity(notificationDto);
        return toDto(notificationRepository.save(notification));
    }

    @Override
    public void deletenotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public NotificationDto toDto(Notification entity) {
        return NotificationDto.builder()
                .id(entity.getId())
                .header(entity.getHeader())
                .body(entity.getBody())
                .active(entity.isActive())
                .build();
    }
    public static Notification toEntity(NotificationDto dto) {
        Notification notification = new Notification();
        notification.setId(dto.getId());
        notification.setHeader(dto.getHeader());
        notification.setBody(dto.getBody());
        notification.setActive(dto.isActive());
        return notification;
    }
}
