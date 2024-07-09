package com.jcode_development.magalums.service;

import com.jcode_development.magalums.model.notification.Notification;
import org.springframework.beans.factory.annotation.Value;
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
	
	public String sendSimpleMessage(Notification notification) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(notification.getDestination());
		message.setText(notification.getMessage());
		message.setSubject("Magalums Notification");
		
		mailSender.send(message);
		return "Email successfully sent";
	}
}
