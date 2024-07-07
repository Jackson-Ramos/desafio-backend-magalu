package com.jcode_development.magalums.controller;

import com.jcode_development.magalums.model.notification.NotificationRequest;
import com.jcode_development.magalums.model.notification.NotificationResponse;
import com.jcode_development.magalums.service.NotificationServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	private final NotificationServices notificationServices;
	
	public NotificationController(NotificationServices notificationServices) {
		this.notificationServices = notificationServices;
	}
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody NotificationRequest data) {
		return this.notificationServices.save(data);
	}
	
	@GetMapping()
	public ResponseEntity<Set<NotificationResponse>> getNotifications() {
		return notificationServices.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotificationResponse> getNotification(@PathVariable("id") String id) {
		return notificationServices.findById(id);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> cancel(@PathVariable("id") String id) {
		return notificationServices.cancel(id);
	}
}
