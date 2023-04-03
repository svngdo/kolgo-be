package com.dtu.kolgo.util.constant;

public enum JwtKey {

    USERNAME,
    EMAIL,
    PASSWORD,
    GRANT_TYPE,
    FIRST_NAME,
    LAST_NAME,
    ENTERPRISE_NAME;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
