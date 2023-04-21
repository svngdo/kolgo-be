package com.dtu.kolgo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"data", "error"})
public class ApiResponse {

    private Map<String, Object> error = new HashMap<>();
    private Map<String, Object> data = new HashMap<>();

    public ApiResponse(String message) {
        this.data.put("message", message);
    }

    public ApiResponse(Map<String, Object> data, Map<String, Object> error) {
        this.data = data;
        this.error = error;
    }

}
