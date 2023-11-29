package com.example.sdmisoback.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    
    USER_READ("user:read"),
    USER_UPDATE("user:update")

    ;

    @Getter
    private final String permission;
}
