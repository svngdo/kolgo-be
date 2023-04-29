package com.dtu.kolgo.util;

import com.dtu.kolgo.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
@Slf4j
public class FileUtils {

    public static void saveImage(String uploadDir, String fileName, MultipartFile file) {
        Path uploadPath = Path.of(uploadDir);
        try (InputStream inputStream = file.getInputStream()) {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("FileUtils.java Exception", e);
            throw new FileException("Could not save image " + fileName);
        }
    }

}
