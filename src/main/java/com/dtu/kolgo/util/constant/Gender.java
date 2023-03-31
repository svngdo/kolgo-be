package com.dtu.kolgo.util.constant;

public enum Gender {

    MALE,
    FEMALE,
    OTHERS;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
