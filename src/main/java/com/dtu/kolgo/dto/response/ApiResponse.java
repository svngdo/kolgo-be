package com.dtu.kolgo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
