package com.jcode_development.magalums.model.notification;

import java.time.LocalDateTime;

public record NotificationResponse(LocalDateTime time, String destination, String message, Long channelId, Long statusId) {
}
