package com.jcode_development.magalums.scheduling;

import com.jcode_development.magalums.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationScheduling {

    private final NotificationService notificationService;

    public NotificationScheduling(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationScheduling.class);

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void runTasks() {

        var dateTime = LocalDateTime.now();
        LOGGER.info("Running at {}", dateTime);

        notificationService.checkAndSend(dateTime);
    }
}
