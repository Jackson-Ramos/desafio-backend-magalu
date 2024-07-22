package com.jcode_development.magalums.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 6430255059023022780L;

    private String id;
    private String login;

}
