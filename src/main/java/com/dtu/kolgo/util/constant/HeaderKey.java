package com.dtu.kolgo.util.constant;

public enum HeaderKey {

    AUTHORIZATION;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
