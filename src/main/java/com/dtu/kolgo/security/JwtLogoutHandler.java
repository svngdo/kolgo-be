package com.dtu.kolgo.security;

import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.service.TokenService;
import com.dtu.kolgo.util.constant.GrantTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtLogoutHandler implements LogoutHandler {

    private final JwtProvider jwtProvider;
    private final TokenService tokenService;

    @SneakyThrows
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        String accessToken = jwtProvider.resolveToken(request);
        String refreshToken = request.getParameter(GrantTypes.REFRESH_TOKEN.toString());

        // validate access token & refresh token
        jwtProvider.validate(accessToken);
        jwtProvider.validateGrantType(accessToken, GrantTypes.ACCESS_TOKEN);
        jwtProvider.validate(refreshToken);
        jwtProvider.validateGrantType(refreshToken, GrantTypes.REFRESH_TOKEN);

        tokenService.revoke(refreshToken);

        SecurityContextHolder.clearContext();
        System.out.println("Cleared security context");

        final ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), new ApiResponse("Logged out successfully!!"));
    }

}
