package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.MailDetails;
import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.response.TokenResponse;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.ExpiredException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.UserException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.security.JwtProvider;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.constant.GrantTypes;
import com.dtu.kolgo.util.constant.Roles;
import com.dtu.kolgo.util.env.JwtEnv;
import com.dtu.kolgo.util.env.ServerEnv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private final KolService kolService;
    private final EnterpriseRepository enterpriseRepo;
    private final TokenService tokenService;

    @Override
    public WebResponse register(RegisterRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ExistsException("Email already in use");
        }

        String verifyAccountToken = jwtProvider.generateVerifyAccountToken(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        String url = String.format("http://%s:3000/verify_account?biz=%b&verify_account_token=%s",
                ServerEnv.HOST, request.isBiz(), verifyAccountToken);
        String subject = "Verify your registration";
        String body = """
                Dear [[name]],<br><br>
                Please click the link below to verify your registration<br>
                <h3><a href="[[url]]" target="_blank">VERIFY ACCOUNT</a></h3>
                Thank you,<br><br>
                KOLgo.
                """;
        body = body.replace("[[name]]", request.getFirstName());
        body = body.replace("[[url]]", url);

        mailService.send(new MailDetails(request.getEmail(), subject, body), true);

        return new WebResponse("A verify email was sent to " + request.getEmail());
    }

    @Override
    public WebResponse verify(String token, boolean isBiz) {
        jwtProvider.validate(token);
        jwtProvider.validateGrantType(token, GrantTypes.VERIFY_ACCOUNT_TOKEN);

        String email = jwtProvider.extractEmail(token);
        if (userRepo.existsByEmail(email)) {
            throw new ExpiredException("This link has already been used or expired");
        }

        User user = userRepo.save(User.builder()
                .firstName(jwtProvider.extractFirstName(token))
                .lastName(jwtProvider.extractLastName(token))
                .email(email)
                .password(jwtProvider.extractPassword(token))
                .build());
        if (isBiz) {
            user.setRoles(List.of(new Role(Roles.ENTERPRISE.name())));
            enterpriseRepo.save(Enterprise.builder()
                    .user(user)
                    .build());
        } else {
            user.setRoles(List.of(new Role(Roles.KOL.name())));
            kolService.save(Kol.builder()
                    .user(user)
                    .build());
        }

        return new WebResponse("Account has been verified");
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = userService.getByEmail(request.getEmail());

        authenticate(user.getId(), request.getPassword());

        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

        tokenService.save(Token.builder()
                .value(newRefreshToken)
                .expiresIn(LocalDateTime.now().plusDays(JwtEnv.DAY_EXPIRATION))
                .user(user)
                .build());

        return UserResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .token(tokenResponse)
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
    public TokenResponse refreshToken(String refreshToken) {
        // validate refresh token
        jwtProvider.validate(refreshToken);
        jwtProvider.validateGrantType(refreshToken, GrantTypes.REFRESH_TOKEN);

        // get user from token
        User user = userService.getById(jwtProvider.extractUserId(refreshToken));

        tokenService.revoke(refreshToken);

        // generate a new pair of access token and refresh token
        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        // save new refresh token
        tokenService.save(Token.builder()
                .value(newRefreshToken)
                .expiresIn(LocalDateTime.now().plusDays(JwtEnv.DAY_EXPIRATION))
                .user(user)
                .build());

        // return new RefreshTokenResponse
        return new TokenResponse(newAccessToken, newRefreshToken);
    }

    public WebResponse forgotPassword(EmailRequest request) {
        User user = userService.getByEmail(request.getEmail());

        String resetPasswordToken = jwtProvider.generateResetPasswordToken(user);
        String url = String.format("http://%s:3000/reset_password?reset_password_token=%s",
                ServerEnv.HOST, resetPasswordToken);
        String subject = "Reset password";
        String body = """
                Dear [[name]],<br><br>
                Please click the link below to reset your password<br>
                <h3><a href="[[url]]" target="_blank">RESET PASSWORD</a></h3>
                Thank you,<br><br>
                KOLgo.
                """;
        body = body.replace("[[name]]", user.getUsername());
        body = body.replace("[[url]]", url);

        mailService.send(new MailDetails(request.getEmail(), subject, body), true);

        return new WebResponse("A reset password email was sent to " + request.getEmail());
    }

    @Override
    public WebResponse resetPassword(String resetPasswordToken, ResetPasswordRequest request) {
        // Validate token
        jwtProvider.validate(resetPasswordToken);
        jwtProvider.validateGrantType(resetPasswordToken, GrantTypes.RESET_PASSWORD_TOKEN);

        // Validate password
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new InvalidException("Password not match");
        }

        // Get user
        int userId = jwtProvider.extractUserId(resetPasswordToken);
        User user = userService.getById(userId);

        // Update password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userService.save(user);

        return new WebResponse("Update password successfully !!");
    }

}
