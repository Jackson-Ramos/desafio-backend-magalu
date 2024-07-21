package com.jcode_development.magalums.model.notification;

import com.jcode_development.magalums.model.channel.Channel;
import com.jcode_development.magalums.model.status.Status;
import com.jcode_development.magalums.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse extends RepresentationModel<NotificationResponse> implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 939777245057785354L;
	
	String id;
	private LocalDateTime time;
	private String destination;
	private String message;
	private Channel channel;
	private Status status;
	private User user;
}
