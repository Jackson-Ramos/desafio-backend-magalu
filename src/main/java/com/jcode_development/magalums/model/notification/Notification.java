package com.jcode_development.magalums.model.notification;

import com.jcode_development.magalums.model.channel.Channel;
import com.jcode_development.magalums.model.status.Status;
import com.jcode_development.magalums.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -4674285576014882223L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
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
