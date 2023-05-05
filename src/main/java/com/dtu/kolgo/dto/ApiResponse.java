package com.dtu.kolgo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"message", "data", "error"})
public class ApiResponse {

    private String message;
    private Map<String, Object> data;
    private Map<String, Object> error;

    public ApiResponse(String message) {
        this.message = message;
    }

}
