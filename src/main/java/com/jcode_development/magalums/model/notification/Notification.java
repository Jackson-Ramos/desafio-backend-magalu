package com.jcode_development.magalums.model.notification;

import com.jcode_development.magalums.model.channel.Channel;
import com.jcode_development.magalums.model.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "message")
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "channel_id")
	private Channel channel;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
}
