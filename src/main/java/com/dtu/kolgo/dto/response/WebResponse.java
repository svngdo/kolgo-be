package com.dtu.kolgo.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "status", "error", "message"})
public class WebResponse {

    @JsonIgnore
    private int code;
    @JsonIgnore
    private String status;
    @JsonProperty("error")
    private Map<String, Object> error;
    @JsonProperty("message")
    private String message;

    public WebResponse(HttpStatus status) {
        this.code = status.value();
        this.message = status.getReasonPhrase();
    }

    public WebResponse(String message) {
        this.message = message;
    }

    public WebResponse(HttpStatus status, Map<String, Object> error) {
        this.code = status.value();
        this.error = error;
    }

    public WebResponse(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
    }

}
