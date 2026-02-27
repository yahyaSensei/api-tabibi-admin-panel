package com.sensei.apitabibiadminpanel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import com.sensei.apitabibiadminpanel.DTO.NotificationRequestDto;
import com.sensei.apitabibiadminpanel.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping(value = "/global", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendGlobalNotification(@RequestBody NotificationRequestDto request) {

        String jsonResult = notificationService.sendGlobalNotification(
                request.title(),
                request.message(),
                request.targetRole()
        );

        // إرجاع 200 OK مع رسالة التأكيد
        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSystemNotifications() {

        // استدعاء الخدمة لجلب الإشعارات
        String jsonResult = notificationService.getSystemNotifications();

        // إرجاع 200 OK مع مصفوفة الإشعارات
        return ResponseEntity.ok(jsonResult);
    }
}