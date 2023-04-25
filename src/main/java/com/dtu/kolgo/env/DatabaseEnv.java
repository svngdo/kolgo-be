package com.dtu.kolgo.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseEnv {

    public static String HOST;
    public static String PORT;
    public static String NAME;
    public static String USERNAME;
    public static String PASSWORD;

    @Value("${DB_HOST}")
    public void setHost(String host) {
        HOST = host;
    }

    @Value("${DB_PORT}")
    public void setPort(String port) {
        PORT = port;
    }

    @Value("${DB_NAME}")
    public void setName(String name) {
        NAME = name;
    }

    @Value("${DB_USERNAME}")
    private void setUsername(String username) {
        USERNAME = username;
    }

    @Value("${DB_PASSWORD}")
    private void setPassword(String password) {
        PASSWORD = password;
    }

}
