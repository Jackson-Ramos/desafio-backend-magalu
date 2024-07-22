package com.jcode_development.magalums.model.notification;

import com.jcode_development.magalums.model.channel.Channel;
import com.jcode_development.magalums.model.status.Status;
import com.jcode_development.magalums.model.user.UserResponse;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse extends RepresentationModel<NotificationResponse> implements Serializable {

    @Serial
    private static final long serialVersionUID = 939777245057785354L;

    private String id;
    private LocalDateTime time;
    private String destination;
    private String message;
    private Channel channel;
    private Status status;
    private UserResponse user;
}
