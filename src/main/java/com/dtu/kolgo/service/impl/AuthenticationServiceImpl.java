package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.MailDetails;
import com.dtu.kolgo.dto.request.LoginRequest;
import com.dtu.kolgo.dto.request.RegisterRequest;
import com.dtu.kolgo.dto.response.LoginResponse;
import com.dtu.kolgo.dto.response.RefreshTokenResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.ExpiredException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.exception.UserException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.repository.KolRepository;
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
import java.util.Collections;
import java.util.UUID;

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
    private final KolRepository kolRepo;
    private final EnterpriseRepository enterpriseRepo;

    @Override
    public WebResponse register(RegisterRequest request) {
        System.out.println(request.isBiz());
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new ExistsException("Email already in use");
        }

        String verifyAccountToken = jwtProvider.generateVerifyAccountToken(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        String url = String.format("http://%s:%s%s/auth/verify?biz=%b&verify_account_token=%s",
                Server.HOST, Server.PORT, Server.CONTEXT_PATH, request.isBiz(), verifyAccountToken);
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
        jwtProvider.validateGrantType(token, GrantType.VERIFY_ACCOUNT_TOKEN);

        String email = jwtProvider.extractEmail(token);
        if (userRepo.existsByEmail(email)) {
            throw new ExpiredException("This link has already been used or expired");
        }

        User user = userRepo.save(User.builder()
                .username(UUID.randomUUID().toString())
                .firstName(jwtProvider.extractFirstName(token))
                .lastName(jwtProvider.extractLastName(token))
                .email(email)
                .password(jwtProvider.extractPassword(token))
                .build());
        if (isBiz) {
            user.setRoles(Collections.singletonList(Role.ENTERPRISE));
            enterpriseRepo.save(Enterprise.builder()
                    .user(user)
                    .build());
        } else {
            user.setRoles(Collections.singletonList(Role.KOL));
            kolRepo.save(Kol.builder()
                    .user(user)
                    .build());
        }

        return new WebResponse("Account has been verified");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userService.fetch(request.getEmail());

        authenticate(user.getId(), request.getPassword());

        String newAccessToken = jwtProvider.generateAccessToken(user);
        String newRefreshToken = jwtProvider.generateRefreshToken(user);

        tokenRepo.save(Token.builder()
                .value(newRefreshToken)
                .expiresIn(LocalDateTime.now().plusDays(Jwt.DB_TOKEN_EXPIRATION))
                .user(user)
                .build());

        return LoginResponse.builder()
                .token(new RefreshTokenResponse(newAccessToken, TokenType.BEARER.toString(), newRefreshToken))
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public void authenticate(long id, String password) {
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
        User user = userService.fetch(jwtProvider.extractUserId(refreshToken));

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
        return new RefreshTokenResponse(newAccessToken, TokenType.BEARER.toString(), newRefreshToken);
    }


}
