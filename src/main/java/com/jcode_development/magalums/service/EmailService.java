package com.jcode_development.magalums.service;

import com.jcode_development.magalums.model.notification.Notification;
import com.jcode_development.magalums.model.status.Status;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMessage(Notification notification) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(notification.getDestination());
            message.setText(notification.getMessage());
            message.setSubject("Magalums Notification");

            mailSender.send(message);
            notification.setStatus(new Status(2L, "SUCCESS"));

        } catch (MailException e) {

            notification.setStatus(new Status(3L, "ERROR"));

        }
    }
}
