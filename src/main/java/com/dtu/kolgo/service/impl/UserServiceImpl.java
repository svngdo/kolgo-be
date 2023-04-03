package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.MailDetails;
import com.dtu.kolgo.dto.request.ChangePasswordRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.exception.ValidationException;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.security.JwtProvider;
import com.dtu.kolgo.service.MailService;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.constant.GrantType;
import com.dtu.kolgo.util.env.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MailService mailService;

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public User fetch(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User ID not found: " + id));
    }

    @Override
    public User fetch(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found: " + email));
    }

    public WebResponse resetPassword(ResetPasswordRequest request) {
        User user = fetch(request.getEmail());

        String resetPasswordToken = jwtProvider.generateResetPasswordToken(user);
        String url = String.format("http://%s:%s%s/auth/update_password?reset_password_token=%s",
                Server.HOST, Server.PORT, Server.CONTEXT_PATH, resetPasswordToken);
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
    public WebResponse updatePassword(String resetPasswordToken, UpdatePasswordRequest request) {
        // Validate token
        jwtProvider.validate(resetPasswordToken);
        jwtProvider.validateGrantType(resetPasswordToken, GrantType.RESET_PASSWORD_TOKEN);

        // Validate password
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new InvalidException("Password not match");
        }

        // Get user
        long userId = jwtProvider.extractUserId(resetPasswordToken);
        User user = fetch(userId);

        // Update password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        save(user);

        return new WebResponse("Update password successfully!!");
    }

    @Override
    public WebResponse changePassword(Principal principal, ChangePasswordRequest request) {
        String userId = principal.getName();
        User user = fetch(Long.parseLong(userId));

        Map<String, Object> error = new HashMap<>();
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            error.put("new_password", "not match");
        }
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            error.put("user_password", "incorrect");
        }
        if (!error.isEmpty()) {
            throw new ValidationException(error);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        save(user);

        return new WebResponse("Change password successfully!!");
    }

}
