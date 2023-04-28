package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.PasswordUpdateRequest;
import com.dtu.kolgo.dto.request.UserUpdateRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.env.FileEnv;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Role;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse save(User user) {
        repo.save(user);
        return new ApiResponse("Saved user successfully");
    }

    @Override
    public List<UserResponse> getAllResponses() {
        return repo.findAll().stream()
                .map(user -> getResponse(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public User get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User ID not found: " + id));
    }

    @Override
    public User get(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Email not found: " + email));
    }

    @Override
    public User get(Principal principal) {
        return get(principal.getName());
    }

    @Override
    public UserResponse getResponse(int userId) {
        User user = get(userId);
        return mapEntityToDto(user);
    }

    @Override
    public String getRole(int id) {
        return get(id).getRoles().get(0).getName();
    }

    @Override
    public String getRole(Principal principal) {
        return get(principal).getRoles().get(0).getName();
    }

    @Override
    public ApiResponse update(int userId, UserUpdateRequest request) {
        User user = get(userId);
        updateAvatar(user, request.getAvatar());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());
        repo.save(user);

        return new ApiResponse("Updated successfully User with ID: " + userId);
    }

    @Override
    public ApiResponse updateAvatar(User user, MultipartFile avatar) {
        String msg = "Update avatar failed";
        if (avatar != null) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatar.getOriginalFilename()));
            user.setAvatar(fileName);
            repo.save(user);
            String uploadDir = FileEnv.IMAGE_PATH;
            fileUtils.saveImage(uploadDir, fileName, avatar);
        }
        return new ApiResponse(msg);
    }

    @Override
    public ApiResponse updateEmail(Principal principal, EmailRequest request) {
        if (repo.existsByEmail(request.getEmail())) {
            throw new ExistsException("Email already in use: " + request.getEmail());
        }
        User user = get(principal);
        user.setEmail(request.getEmail());
        repo.save(user);

        return new ApiResponse("Updated email successfully");
    }

    @Override
    public ApiResponse updatePassword(Principal principal, PasswordUpdateRequest request) {
        User user = get(principal);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidException("Incorrect password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repo.save(user);

        return new ApiResponse("Updated password successfully !!");
    }

    @Override
    public ApiResponse delete(int userId) {
        repo.deleteById(userId);
        return new ApiResponse("Deleted successfully User with ID: " + userId);
    }

    @Override
    public UserResponse mapEntityToDto(User user) {
        return UserResponse.builder()
                .id(user.getId())
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
    public List<UserResponse> mapEntityToDto(List<User> users) {
        return users.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

}
