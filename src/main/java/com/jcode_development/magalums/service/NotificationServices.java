package com.jcode_development.magalums.service;

import com.jcode_development.magalums.model.notification.Notification;
import com.jcode_development.magalums.model.notification.NotificationRequest;
import com.jcode_development.magalums.repository.ChannelRepository;
import com.jcode_development.magalums.repository.NotificationRepository;
import com.jcode_development.magalums.repository.StatusRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices {
	
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
	
	public ResponseEntity<?> save(NotificationRequest data) {
		Notification notification = new Notification();
		notification.setDateTime(data.time());
		notification.setDestination(data.destination());
		notification.setMessage(data.message());
		notification.setChannel(channelRepository.findById(data.channelId()).get());
		notification.setStatus(statusRepository.findById(data.statusId()).get());
		
		notificationRepository.save(notification);
		return ResponseEntity.accepted().build();
	}
}
