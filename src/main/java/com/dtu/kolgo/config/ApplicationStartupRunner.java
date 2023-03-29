package com.dtu.kolgo.config;

import com.dtu.kolgo.util.env.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("=============================================");
        System.out.printf("KOLgo is running at http://%s:%s/api%n", Server.HOST, Server.PORT);
        System.out.println("=============================================");
    }

}
