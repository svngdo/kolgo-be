package com.dtu.kolgo.util.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Server {

    public static String HOST;
    public static String PORT;
    public static String CONTEXT_PATH;

    @Value("${SERVER_HOST}")
    private void setHost(String host) {
        HOST = host;
    }

    @Value("${SERVER_PORT}")
    private void setPort(String port) {
        PORT = port;
    }

    @Value("${SERVER_CONTEXT_PATH}")
    private void setContextPath(String contextPath) {
        CONTEXT_PATH = contextPath;
    }

}
