package org.graduatetrackingsystem.graduatetracking.controllers;

import org.graduatetrackingsystem.graduatetracking.dtos.NotificationDto;
import org.graduatetrackingsystem.graduatetracking.dtos.NotificationRequest;
import org.graduatetrackingsystem.graduatetracking.dtos.StudentDto;
import org.graduatetrackingsystem.graduatetracking.entities.Notification;
import org.graduatetrackingsystem.graduatetracking.reponses.NotificationResponse;

import org.graduatetrackingsystem.graduatetracking.services.NotifitaionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notification")
@CrossOrigin
public class NotificationController {
    @Autowired
    NotifitaionService notificationService;
    @PostMapping("/create")
    public NotificationResponse createNotification(@RequestBody NotificationRequest newNotificationRequest) {
        return toResponse(notificationService.createNotification(newNotificationRequest.toDto()));
    }
    @GetMapping("/getAllNotifications")
    public List<NotificationResponse> getBasketList(){
        List<NotificationResponse> getNotificationList= notificationService.getAllNotification().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return getNotificationList;
    }
    @DeleteMapping("/deleteNotification/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        try {
            // Perform deletion logic
            notificationService.deletenotification(id);
            return ResponseEntity.ok().body("Notification deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete notification");
        }
    }

    @PutMapping("/update/{id}")
    public  NotificationResponse updateNotification(@PathVariable String id, @RequestBody NotificationRequest request) {
        NotificationDto dto = request.toDto();
        dto.setId(Long.parseLong(id));
        return toResponse(notificationService.updateNotification(Long.parseLong(id), dto));
    }


    public NotificationResponse toResponse(NotificationDto dto) {
        // Return a new StudentResponse object, using the builder pattern
        return NotificationResponse.builder()
                .id(dto.getId())
                .header(dto.getHeader())
                .body(dto.getBody())
                .active(dto.isActive())
                .build();
        }

    }

