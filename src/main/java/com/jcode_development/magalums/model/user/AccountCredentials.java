package com.jcode_development.magalums.model.user;

import jakarta.validation.constraints.NotBlank;

public record AccountCredentials(
        @NotBlank String login,
        @NotBlank String password
) {
}
