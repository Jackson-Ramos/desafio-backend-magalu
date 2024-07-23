package com.jcode_development.magalums.controller;

import com.jcode_development.magalums.model.notification.NotificationRequest;
import com.jcode_development.magalums.model.notification.NotificationResponse;
import com.jcode_development.magalums.service.NotificationService;
import com.jcode_development.magalums.swagger.NotificationDocumentation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/notification")
public class NotificationController implements NotificationDocumentation {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<String> save(@RequestBody NotificationRequest data) {
        return this.notificationService.save(data);
    }

    //    Notificações de todos os usuarios
    @GetMapping(value = "all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Set<NotificationResponse>> getNotifications() {
        return notificationService.findAll();
    }

    //    Notificações apenas do usuario
    @GetMapping(value = "user", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Set<NotificationResponse>> getUserNotifications() {
        return notificationService.findNotificationsByUser();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NotificationResponse> getNotification(@PathVariable("id") String id) {
        return notificationService.findById(id);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> cancel(@PathVariable("id") String id) {
        return notificationService.cancel(id);
    }
}
