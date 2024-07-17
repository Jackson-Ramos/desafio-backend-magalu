package com.jcode_development.magalums.model.user;

import lombok.Getter;

@Getter
public enum Permissions {

    ADMIN("admin"),
    MANAGER("manager"),
    USER("user");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

}
