package com.dtu.kolgo.exception;

import com.dtu.kolgo.dto.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    private final GlobalExceptionHandler exceptionHandler;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomJwtException e) {
            mapErrorToResponse(HttpServletResponse.SC_UNAUTHORIZED, exceptionHandler.handleJwtException(e), response);
        } catch (NotFoundException e) {
            mapErrorToResponse(HttpServletResponse.SC_NOT_FOUND, exceptionHandler.handleNotFoundException(e), response);
        } catch (InvalidException e) {
            mapErrorToResponse(HttpServletResponse.SC_FORBIDDEN, exceptionHandler.handleInvalidException(e), response);
        } catch (Exception e) {
            log.error("FilterChainExceptionHandler.java Exception", e);
            mapErrorToResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exceptionHandler.handleException(e), response);
        }
    }

    private void mapErrorToResponse(
            int statusCode,
            ApiResponse error,
            HttpServletResponse response
    ) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(statusCode);
        mapper.writeValue(response.getOutputStream(), error);
    }

}
