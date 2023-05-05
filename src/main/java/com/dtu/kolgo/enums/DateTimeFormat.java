package com.dtu.kolgo.enums;

public enum DateTimeFormat {

    SIMPLE("yyyyMMddHHmmss");

    public final String format;

    DateTimeFormat(String format) {
        this.format = format;
    }

    public static String getSimple() {
        return SIMPLE.format;
    }

}
