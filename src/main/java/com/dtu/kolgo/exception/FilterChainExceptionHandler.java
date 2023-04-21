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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    private final GlobalExceptionHandler handler;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomJwtException e) {
            mapErrorToResponse(HttpStatus.UNAUTHORIZED, handler.handleJwtException(e), response);
        } catch (NotFoundException e) {
            mapErrorToResponse(HttpStatus.NOT_FOUND, handler.handleNotFoundException(e), response);
        } catch (InvalidException e) {
            mapErrorToResponse(HttpStatus.FORBIDDEN, handler.handleInvalidException(e), response);
        } catch (Exception e) {
            log.error("FilterChainExceptionHandler.java Exception", e);
            mapErrorToResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    new ApiResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase()),
                    response
            );
        }
    }

    private void mapErrorToResponse(
            HttpStatus status,
            ApiResponse error,
            HttpServletResponse response
    ) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        mapper.writeValue(response.getOutputStream(), error);
    }

}
