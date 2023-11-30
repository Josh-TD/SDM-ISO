package com.example.sdmisoback.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.sdmisoback.security.user.Permission.USER_READ;
import static com.example.sdmisoback.security.user.Permission.USER_UPDATE;

@RequiredArgsConstructor
public enum Role {

    USER(
        Set.of(
            USER_READ,
            USER_UPDATE
        )
    ),

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}