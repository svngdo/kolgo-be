package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.UserUpdateRequest;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Role;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.FileUtils;
import com.dtu.kolgo.env.FileEnv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final FileUtils fileUtils;

    @Override
    public void save(User user) {
        repo.save(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return repo.findAll().stream()
                .map(user -> getResponseById(user.getId()))
                .collect(Collectors.toList());
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
    public User getByPrincipal(Principal principal) {
        return getByEmail(principal.getName());
    }

    @Override
    public UserResponse getResponseById(int userId) {
        User user = getById(userId);

        return UserResponse.builder()
                .id(userId)
                .avatar(user.getAvatar())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ApiResponse update(int userId, UserUpdateRequest request) {
        User user = getById(userId);
        updateAvatar(user, request.getAvatar());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());
        repo.save(user);

        return new ApiResponse("Updated successfully User with ID: " + userId);
    }

    @Override
    public void updateAvatar(User user, MultipartFile avatar) {
        if (avatar == null) return;
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatar.getOriginalFilename()));
        user.setAvatar(fileName);
        repo.save(user);
        String uploadDir = FileEnv.IMAGE_PATH + "/" + user.getId() + " - " + user.getEmail();
        fileUtils.saveImage(uploadDir, fileName, avatar);
    }

    @Override
    public ApiResponse delete(int userId) {
        repo.deleteById(userId);
        return new ApiResponse("Deleted successfully User with ID: " + userId);
    }

    @Override
    public void validateEmail(String email) {
        if (repo.existsByEmail(email)) {
            throw new ExistsException("Email already in use: " + email);
        }
    }

}
