package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.ChangePasswordRequest;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.exception.ValidationException;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.service.UserService;
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

    @Override
    public User get(String input) {
        User user;
        try {
            int id = Integer.parseInt(input);
            user = repo.findById(id)
                    .orElseThrow(() -> new NotFoundException("User ID not found: " + input));
        } catch (NumberFormatException e) {
            if (input.contains("@")) {
                user = repo.findByEmail(input)
                        .orElseThrow(() -> new NotFoundException("Email not found: " + input));
            } else {
                user = repo.findByUsername(input)
                        .orElseThrow(() -> new NotFoundException("Username not found: " + input));
            }
        }
        return user;
    }

    @Override
    public WebResponse changePassword(Principal principal, ChangePasswordRequest request) {
        String userId = principal.getName();
        User user = repo.findById(Integer.parseInt(userId))
                .orElseThrow(() -> new NotFoundException("User ID not found: " + userId));

        Map<String, Object> error = new HashMap<>();
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            error.put("new_password", "Not match");
        }
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            error.put("user_password", "Incorrect");
        }
        if (!error.isEmpty()) {
            throw new ValidationException(error);
        }

        return updatePassword(user, request.getNewPassword());
    }

    @Override
    public WebResponse updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        repo.save(user);
        return new WebResponse("Update password successfully!!");
    }

}
