package com.jcode_development.magalums.model.notification;

import com.jcode_development.magalums.model.channel.Channel;
import com.jcode_development.magalums.model.status.Status;

import java.time.LocalDateTime;

public record NotificationResponse(
		LocalDateTime time,
		String destination,
		String message,
		Channel channel,
		Status status) {
}
