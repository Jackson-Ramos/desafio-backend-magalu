package com.jcode_development.magalums.model.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record NotificationRequest(
        @NotNull LocalDateTime time,
        @NotBlank String destination,
        @NotBlank String message,
        @NotNull Long channelId) {
}
