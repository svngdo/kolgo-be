package com.dtu.kolgo.exception;

import com.dtu.kolgo.dto.response.WebResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static void createHttpServletResponse(
            HttpServletResponse res,
            HttpStatus httpStatus,
            String msg
    ) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        WebResponse body = new WebResponse(httpStatus, msg);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus(httpStatus.value());
        mapper.writeValue(res.getOutputStream(), body);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new WebResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserException.class)
    public WebResponse handleUserException(UserException e) {
        return new WebResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public WebResponse handleValidationException(ValidationException ex) {
        return new WebResponse(HttpStatus.BAD_REQUEST, ex.getError());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(InvalidException.class)
    public WebResponse handleInvalidException(InvalidException e) {
        return new WebResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtException.class)
    public WebResponse handleJwtException(JwtException e) {
        return new WebResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public WebResponse handleNotFoundException(NotFoundException e) {
        return new WebResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

}
