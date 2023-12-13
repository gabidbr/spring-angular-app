package com.example.backend.enumeration;

import lombok.Getter;

import static com.example.backend.constant.Authority.*;

@Getter
public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_HR(HR_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_USER(SUPER_ADMIN_AUTHORITIES);

    private final String[] authorities;

    Role(String ... authorities) {
        this.authorities = authorities;
    }
}
