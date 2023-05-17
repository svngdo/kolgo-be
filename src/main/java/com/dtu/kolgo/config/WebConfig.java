package com.dtu.kolgo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.image-path}")
    private String imagePath;

    @Value("${file.icon-path}")
    private String iconPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:///" + imagePath);

        registry.addResourceHandler("/icons/**")
                .addResourceLocations("file:///" + iconPath);
    }

}
