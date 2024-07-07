package com.jcode_development.magalums.service;

import com.jcode_development.magalums.mapper.Mapper;
import com.jcode_development.magalums.model.notification.Notification;
import com.jcode_development.magalums.model.notification.NotificationRequest;
import com.jcode_development.magalums.model.notification.NotificationResponse;
import com.jcode_development.magalums.model.status.Status;
import com.jcode_development.magalums.repository.ChannelRepository;
import com.jcode_development.magalums.repository.NotificationRepository;
import com.jcode_development.magalums.repository.StatusRepository;
import com.jcode_development.magalums.scheduling.NotificationScheduling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Service
public class NotificationServices {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServices.class);
	
	private final NotificationRepository notificationRepository;
	private final ChannelRepository channelRepository;
	private final StatusRepository statusRepository;
	
	public NotificationServices(
			NotificationRepository notificationRepository,
			ChannelRepository channelRepository,
			StatusRepository statusRepository) {
		this.notificationRepository = notificationRepository;
		this.channelRepository = channelRepository;
		this.statusRepository = statusRepository;
	}
	
	public ResponseEntity<String> save(NotificationRequest data) {
		
		LocalDateTime now = LocalDateTime.now();
		if (!data.time().isBefore(now)) {
			
			var channel = channelRepository.findById(data.channelId()).orElseThrow(RuntimeException::new);
			var status = statusRepository.findByStatusId(1L);
			
			Notification notification = new Notification();
			notification.setDateTime(data.time());
			notification.setDestination(data.destination());
			notification.setMessage(data.message());
			notification.setChannel(channel);
			notification.setStatus(status);
			notificationRepository.save(notification);
			
			return ResponseEntity.accepted().build();
		} else {
			return ResponseEntity.badRequest().body("It is not possible to schedule a notification for a date that has already passed");
		}
	}
	
	public ResponseEntity<Set<NotificationResponse>> findAll() {
		var notifications = new HashSet<>(Mapper.parseObjects(notificationRepository.findAll(), NotificationResponse.class));
		return ResponseEntity.ok(notifications);
	}
	
	public ResponseEntity<NotificationResponse> findById(String id) {
		var notification = notificationRepository.findById(id);
		
		if (notification.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		var notificationResponse = Mapper.parseObject(notification.get(), NotificationResponse.class);
		return ResponseEntity.ok().body(notificationResponse);
	}
	
	public ResponseEntity<?> cancel(String id) {
		
		var notification = notificationRepository.findById(id).orElse(null);
		if (notification == null) {
			return ResponseEntity.notFound().build();
		}
		
		var status = statusRepository.findByStatusId(4L);
		notification.setStatus(status);
		
		notificationRepository.save(notification);
		return ResponseEntity.noContent().build();
	}
	
	public void checkAndSend() {
		
		String ERROR_STATUS = "ERROR";
		String PENDING_STATUS = "PENDING";
		LocalDateTime currentDateTime = LocalDateTime.now();
		var notifications = notificationRepository.findNotificationsByStatusAndDateTime(PENDING_STATUS, ERROR_STATUS, currentDateTime);
		
		notifications.forEach(sendNotification());
	}
	
	private Consumer<Notification> sendNotification() {
		var status = statusRepository.findByStatusId(2L);
		return n -> {
		/*
		 TODO send message
		 */
			n.setStatus(status);
			notificationRepository.save(n);
		};
	}
}
