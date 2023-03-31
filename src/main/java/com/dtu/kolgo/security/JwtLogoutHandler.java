package com.dtu.kolgo.security;

import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.repository.TokenRepository;
import com.dtu.kolgo.service.TokenService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.constant.GrantType;
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
    private final TokenRepository tokenRepo;
    private final TokenService tokenService;
    private final UserService userService;

    @SneakyThrows
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        String accessToken = jwtProvider.resolveToken(request);
        String refreshToken = request.getParameter(GrantType.REFRESH_TOKEN.toString());

        // validate access token & refresh token
        jwtProvider.validate(accessToken);
        jwtProvider.validateGrantType(accessToken, GrantType.ACCESS_TOKEN);
        jwtProvider.validate(refreshToken);
        jwtProvider.validateGrantType(refreshToken, GrantType.REFRESH_TOKEN);

        tokenService.revoke(refreshToken);

        SecurityContextHolder.clearContext();
        System.out.println("Cleared security context");

        final ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        mapper.writeValue(response.getOutputStream(), new WebResponse("Logged out successfully!!"));
    }

}
