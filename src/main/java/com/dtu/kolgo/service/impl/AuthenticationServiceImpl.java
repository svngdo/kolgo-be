package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.LoginResponse;
import com.dtu.kolgo.dto.response.RefreshTokenResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.exception.UserException;
import com.dtu.kolgo.exception.ValidationException;
import com.dtu.kolgo.model.MailDetails;
import com.dtu.kolgo.model.Role;
import com.dtu.kolgo.model.Token;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.TokenRepository;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.security.JwtProvider;
import com.dtu.kolgo.service.AuthenticationService;
import com.dtu.kolgo.service.MailService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.constant.GrantType;
import com.dtu.kolgo.util.constant.TokenType;
import com.dtu.kolgo.util.env.Jwt;
import com.dtu.kolgo.util.env.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MailService mailService;
    private final UserService userService;
    private final UserRepository userRepo;
    private final TokenRepository tokenRepo;

    @Override
    public WebResponse register(RegisterRequest request) {
        Map<String, Object> error = new HashMap<>();
        if (userRepo.existsByUsername(request.getUsername())) {
            error.put("username", "Already in use");
        }
        if (userRepo.existsByEmail(request.getEmail())) {
            error.put("email", "Already in use");
        }
        if (!error.isEmpty()) {
            throw new ValidationException(error);
        }

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(List.of(Role.USER))
                .build();
        userRepo.save(user);

        return new WebResponse("Registered successfully");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userService.get(request.getUserInput());

        authenticate(user.getId(), request.getPassword());

        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        tokenRepo.save(Token.builder()
                .value(newRefreshToken)
                .expiresIn(LocalDateTime.now().plusDays(Jwt.DB_TOKEN_EXPIRATION))
                .user(user)
                .build());

        return LoginResponse.builder()
                .token(new RefreshTokenResponse(newAccessToken, TokenType.BEARER, newRefreshToken))
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void authenticate(int id, String password) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(id, password)
            );
        } catch (DisabledException e) {
            throw new UserException("Account has been disabled.");
        } catch (LockedException e) {
            throw new UserException("Account has been locked.");
        } catch (BadCredentialsException e) {
            throw new UserException("Invalid username/email or password.");
        } catch (Exception e) {
            log.error("Authentication Service Exception", e);
        }
    }

    @Override
    public RefreshTokenResponse refreshToken(String refreshToken) {
        // validate refresh token
        jwtProvider.validate(refreshToken);
        jwtProvider.validateGrantType(refreshToken, GrantType.REFRESH_TOKEN);

        // get user from token
        User user = userService.get(jwtProvider.extractUserId(refreshToken));

        if (tokenRepo.existsByValue(refreshToken)) {
            tokenRepo.deleteByValue(refreshToken);
            System.out.println("Delete single token: " + refreshToken);
        } else {
            tokenRepo.deleteAllByUser(user);
            System.out.println("Delete all tokens of user " + user.getUsername());
            throw new NotFoundException("Refresh token not found: " + refreshToken);
        }

        // generate a new pair of access token and refresh token
        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        // save new refresh token
        tokenRepo.save(Token.builder()
                .value(newRefreshToken)
                .expiresIn(LocalDateTime.now().plusDays(Jwt.DB_TOKEN_EXPIRATION))
                .user(user)
                .build());

        // return new RefreshTokenResponse
        return new RefreshTokenResponse(newAccessToken, TokenType.BEARER, newRefreshToken);
    }

    public WebResponse resetPassword(ResetPasswordRequest request) {
        User user = userService.get(request.getEmail());

        String resetPasswordToken = jwtProvider.generateResetPasswordToken(user);
        String url = String.format("http://%s:%s%s/auth/update-password?reset_password_token=%s",
                Server.HOST, Server.PORT, Server.CONTEXT_PATH, resetPasswordToken);
        String body = "Follow the link to reset your password:\n" + url;
        MailDetails mailDetails = MailDetails.builder()
                .recipient(request.getEmail())
                .subject("Reset password")
                .body(body)
                .build();

        mailService.sendSimpleMail(mailDetails);

        return new WebResponse("An email was sent to " + request.getEmail());
    }

    @Override
    public WebResponse updatePassword(String resetPasswordToken, UpdatePasswordRequest request) {
        // Validate token
        jwtProvider.validate(resetPasswordToken);
        jwtProvider.validateGrantType(resetPasswordToken, GrantType.RESET_PASSWORD_TOKEN);

        // Validate password
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new InvalidException("Password not match");
        }

        // Get user
        String userId = jwtProvider.extractUserId(resetPasswordToken);
        User user = userRepo.findById(Integer.parseInt(userId))
                .orElseThrow(() -> new NotFoundException("User ID not found: " + userId));

        // Update password
        return userService.updatePassword(user, request.getNewPassword());
    }

}
