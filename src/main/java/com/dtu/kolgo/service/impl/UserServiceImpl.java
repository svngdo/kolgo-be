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
    public void save(User user) {
        repo.save(user);
    }

    @Override
    public User getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User ID not found: " + id));
    }

    @Override
    public User getByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found: " + email));
    }

    @Override
    public User getByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Username not found: " + username));
    }

    @Override
    public WebResponse changePassword(Principal principal, ChangePasswordRequest request) {
        String userId = principal.getName();
        User user = getById(Integer.parseInt(userId));

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
