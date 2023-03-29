package com.dtu.kolgo.util.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Mail {

    public static String SENDER;
    public static String DISPLAY_NAME;

    @Value("${MAIL_USERNAME}")
    private void setSender(String sender) {
        SENDER = sender;
    }

    @Value("${MAIL_DISPLAY_NAME}")
    private void setDisplayName(String displayName) {
        DISPLAY_NAME = displayName;
    }

}