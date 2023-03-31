package com.dtu.kolgo.util.constant;

public enum JwtKey {

    PASSWORD,
    GRANT_TYPE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
