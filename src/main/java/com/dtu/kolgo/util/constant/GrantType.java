package com.dtu.kolgo.util.constant;

public enum GrantType {

    ACCESS_TOKEN,
    REFRESH_TOKEN,
    RESET_PASSWORD_TOKEN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
