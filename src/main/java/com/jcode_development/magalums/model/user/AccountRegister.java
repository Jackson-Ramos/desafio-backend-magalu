package com.jcode_development.magalums.model.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountRegister(
       @NotBlank String login,
       @NotBlank String password,
       @NotNull Permissions permissions) {
}
