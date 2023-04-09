package com.dtu.kolgo.util.constant;

public enum GrantTypes {

    ACCESS_TOKEN,
    REFRESH_TOKEN,
    RESET_PASSWORD_TOKEN,
    VERIFY_ACCOUNT_TOKEN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
