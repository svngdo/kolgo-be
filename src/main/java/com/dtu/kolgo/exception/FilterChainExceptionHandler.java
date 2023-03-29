package com.dtu.kolgo.exception;

import com.dtu.kolgo.dto.response.WebResponse;
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
        } catch (JwtException e) {
            mapErrorToResponse(handler.handleJwtException(e), response);
        } catch (NotFoundException e) {
            mapErrorToResponse(handler.handleNotFoundException(e), response);
        } catch (InvalidException e) {
            mapErrorToResponse(handler.handleInvalidException(e), response);
        } catch (Exception e) {
            log.error("Spring Security Filter Chain Exception", e);
            mapErrorToResponse(
                    new WebResponse(HttpStatus.UNAUTHORIZED),
                    response
            );
        }
    }

    private void mapErrorToResponse(
            WebResponse error,
            HttpServletResponse response
    ) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(error.getCode());
        mapper.writeValue(response.getOutputStream(), error);
    }

}
