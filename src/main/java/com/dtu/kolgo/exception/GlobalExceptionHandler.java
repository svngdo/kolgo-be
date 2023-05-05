package com.dtu.kolgo.exception;

import com.dtu.kolgo.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e) {
        log.error("Global Exception Handler", e);
        return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null, new HashMap<>() {{
            put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }});
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse handleException(MethodArgumentTypeMismatchException e) {
        return new ApiResponse(e.getMessage(), null, new HashMap<>() {{
            put("code", HttpStatus.BAD_REQUEST.value());
        }});
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ApiResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), null, errors);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserException.class)
    public ApiResponse handleUserException(UserException e) {
        return new ApiResponse(e.getMessage(), null, new HashMap<>() {{
            put("code", HttpStatus.FORBIDDEN.value());
        }});
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ApiResponse handleValidationException(ValidationException e) {
        return new ApiResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), null, e.getError());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidException.class)
    public ApiResponse handleInvalidException(InvalidException e) {
        return new ApiResponse(e.getMessage(), null, new HashMap<>() {{
            put("code", HttpStatus.FORBIDDEN.value());
        }});
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(CustomJwtException.class)
    public ApiResponse handleJwtException(CustomJwtException e) {
        return new ApiResponse(e.getMessage(), null, new HashMap<>() {{
            put("code", HttpStatus.UNAUTHORIZED.value());
        }});
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiResponse handleNotFoundException(NotFoundException e) {
        return new ApiResponse(e.getMessage(), null, new HashMap<>() {{
            put("code", HttpStatus.NOT_FOUND.value());
        }});
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ExistsException.class)
    public ApiResponse handleExistsException(ExistsException e) {
        return new ApiResponse(e.getMessage(), null, new HashMap<>() {{
            put("code", HttpStatus.CONFLICT.value());
        }});
    }

    @ResponseStatus(HttpStatus.GONE)
    @ExceptionHandler(ExpiredException.class)
    public ApiResponse handleExpiredException(ExpiredException e) {
        return new ApiResponse(e.getMessage(), null, new HashMap<>() {{
            put("code", HttpStatus.GONE.value());
        }});
    }

}
