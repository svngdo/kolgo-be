package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.MailDto;
import com.dtu.kolgo.dto.user.*;
import com.dtu.kolgo.enums.GrantType;
import com.dtu.kolgo.enums.Role;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.ExpiredException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.UserException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.security.JwtProvider;
import com.dtu.kolgo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${jwt.exp-day}")
    private int jwtExpDay;
    @Value("${server.host}")
    private String host;

    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MailService mailService;
    private final UserService userService;
    private final UserRepository userRepo;
    private final KolService kolService;
    private final EnterpriseService entService;
    private final TokenService tokenService;
    private final ModelMapper mapper;
    private final FieldService fieldService;

    @Override
    public ApiResponse register(RegisterDto request) {
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
                host, request.isBiz(), verifyAccountToken);
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

        mailService.send(new MailDto(request.getEmail(), subject, body), true);

        return new ApiResponse("A verify email was sent to " + request.getEmail());
    }

    @Override
    public ApiResponse verify(String token, boolean isBiz) {
        jwtProvider.validate(token);
        jwtProvider.validateGrantType(token, GrantType.VERIFY_ACCOUNT_TOKEN);

        String email = jwtProvider.extractEmail(token);
        if (userRepo.existsByEmail(email)) {
            throw new ExpiredException("This link has already been used or expired");
        }

        User user = User.builder()
                .firstName(jwtProvider.extractFirstName(token))
                .lastName(jwtProvider.extractLastName(token))
                .email(email)
                .password(jwtProvider.extractPassword(token))
                .build();
        List<Field> fields = new ArrayList<>() {{
            add(fieldService.getById((short) 1));
        }};
        if (isBiz) {
            user.setRole(Role.ENTERPRISE);
            userService.save(user);
            entService.save(Enterprise.builder()
                    .user(user)
                    .address(new Address())
                    .fields(fields)
                    .campaigns(new ArrayList<>())
                    .build());
        } else {
            user.setRole(Role.KOL);
            userService.save(user);
            kolService.save(Kol.builder()
                    .user(user)
                    .address(new Address())
                    .fields(fields)
                    .images(new ArrayList<>())
                    .bookings(new ArrayList<>())
                    .campaigns(new ArrayList<>())
                    .build());
        }

        return new ApiResponse("Account has been verified");
    }

    @Override
    public AuthDto login(LoginDto request) {
        User user = userService.getByEmail(request.getEmail());

        authenticate(user.getId(), request.getPassword());

        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        TokenDto tokenDto = TokenDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();

        tokenService.save(Token.builder()
                .value(newRefreshToken)
                .expiresIn(LocalDateTime.now().plusDays(jwtExpDay))
                .user(user)
                .build());

        return new AuthDto(
                mapper.map(user, UserDto.class),
                tokenDto
        );
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
    public TokenDto refreshToken(String refreshToken) {
        // validate refresh token
        jwtProvider.validate(refreshToken);
        jwtProvider.validateGrantType(refreshToken, GrantType.REFRESH_TOKEN);

        // get user from token
        User user = userService.getById(jwtProvider.extractUserId(refreshToken));

        tokenService.revoke(refreshToken);

        // generate a new pair of access token and refresh token
        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        // save new refresh token
        tokenService.save(Token.builder()
                .value(newRefreshToken)
                .expiresIn(LocalDateTime.now().plusDays(jwtExpDay))
                .user(user)
                .build());

        // return new RefreshTokenResponse
        return new TokenDto(newAccessToken, newRefreshToken);
    }

    public ApiResponse forgotPassword(EmailDto request) {
        User user = userService.getByEmail(request.getEmail());

        String resetPasswordToken = jwtProvider.generateResetPasswordToken(user);
        String url = String.format("http://%s:3000/reset_password?reset_password_token=%s",
                host, resetPasswordToken);
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

        mailService.send(new MailDto(request.getEmail(), subject, body), true);

        return new ApiResponse("A reset password email was sent to " + request.getEmail());
    }

    @Override
    public ApiResponse resetPassword(String resetPasswordToken, PasswordResetDto request) {
        // Validate token
        jwtProvider.validate(resetPasswordToken);
        jwtProvider.validateGrantType(resetPasswordToken, GrantType.RESET_PASSWORD_TOKEN);

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

        return new ApiResponse("Update password successfully !!");
    }

}
