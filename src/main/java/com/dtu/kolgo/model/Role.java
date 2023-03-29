package com.dtu.kolgo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    KOL,
    ENTERPRISE;

    @Override
    public String getAuthority() {
        return name();
    }
}
