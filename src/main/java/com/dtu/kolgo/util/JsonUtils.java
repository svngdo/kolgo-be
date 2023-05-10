package com.dtu.kolgo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JsonUtils {

    public static Map<String, Object> convertJsonToMap(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        try {
            data = mapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("JsonUtils Exception", e);
        }
        return data;
    }

}
