package com.dtu.kolgo.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationException extends RuntimeException {

    private final Map<String, Object> error;

    public ValidationException(Map<String, Object> error) {
        this.error = error;
    }

}
