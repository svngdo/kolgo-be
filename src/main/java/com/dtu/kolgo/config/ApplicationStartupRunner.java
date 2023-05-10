package com.dtu.kolgo.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {

    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... args) {
        String apiUrl = String.format("http://%s:%s/api", host, port);
        System.out.println("=============================================");
        System.out.println("KOLgo is running at: " + apiUrl);
        System.out.println("Swagger UI: " + apiUrl + "/swagger-ui/index.html");
        System.out.println("=============================================");

    }

}
