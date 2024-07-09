package com.jcode_development.magalums.controller;

import com.jcode_development.magalums.model.notification.NotificationRequest;
import com.jcode_development.magalums.model.notification.NotificationResponse;
import com.jcode_development.magalums.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	private final NotificationService notificationService;
	
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@PostMapping()
	public ResponseEntity<String> save(@RequestBody NotificationRequest data) {
		return this.notificationService.save(data);
	}
	
	@GetMapping()
	public ResponseEntity<Set<NotificationResponse>> getNotifications() {
		return notificationService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotificationResponse> getNotification(@PathVariable("id") String id) {
		return notificationService.findById(id);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<String> cancel(@PathVariable("id") String id) {
		return notificationService.cancel(id);
	}
}
