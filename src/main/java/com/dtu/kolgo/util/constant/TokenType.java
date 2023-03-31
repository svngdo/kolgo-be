package com.dtu.kolgo.util.constant;

public enum TokenType {

    BEARER;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

}
