package com.dtu.kolgo.util;

import com.dtu.kolgo.enums.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtils {

    public static LocalDateTime convertToLocalDateTime(String timestamp) {
        return LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(DateTimeFormat.SIMPLE.format));
    }

    public static String convertToString(LocalDateTime timestamp) {
        return timestamp.format(DateTimeFormatter.ofPattern(DateTimeFormat.SIMPLE.format));
    }

}
