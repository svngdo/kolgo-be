package com.dtu.kolgo.util.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileEnv {

    public static String IMAGE_PATH;

    @Value("${FILE_IMAGE_PATH}")
    private void setImagePath(String path) {
        IMAGE_PATH = path;
    }

}
