package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.EmailDto;
import com.dtu.kolgo.dto.PasswordUpdateDTO;
import com.dtu.kolgo.dto.UserDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.exception.ExistsException;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.UserRepository;
import com.dtu.kolgo.service.UserService;
import com.dtu.kolgo.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${file.image-path}")
    private String imagePath;

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Override
    public ApiResponse save(User user) {
        repo.save(user);
        return new ApiResponse("Saved user successfully");
    }

    @Override
    public List<UserDto> getAllDto() {
        System.out.println("user service");
        return repo.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .toList();
    }

    @Override
    public User get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("User ID not found: " + id));
    }

    @Override
    public UserDto getDto(int id) {
        return mapper.map(get(id), UserDto.class);
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
    public ApiResponse update(int id, UserDto dto, MultipartFile avatar) {
        User user = get(id);
        return update(user, dto, avatar);
    }

    @Override
    public ApiResponse update(User user, UserDto dto, MultipartFile avatar) {
        updateAvatar(user, avatar);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        repo.save(user);

        return new ApiResponse("Updated user successfully");
    }

    @Override
    public void updateAvatar(User user, MultipartFile avatar) {
        if (avatar == null) return;
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(avatar.getOriginalFilename()));
        user.setAvatar(fileName);
        repo.save(user);
        String uploadDir = imagePath;
        FileUtils.saveImage(uploadDir, fileName, avatar);
    }

    @Override
    public ApiResponse updateEmail(Principal principal, EmailDto dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new ExistsException("Email already in use: " + dto.getEmail());
        }
        User user = get(principal);
        user.setEmail(dto.getEmail());
        repo.save(user);

        return new ApiResponse("Updated email successfully");
    }

    @Override
    public ApiResponse updatePassword(Principal principal, PasswordUpdateDTO request) {
        User user = get(principal);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new InvalidException("Incorrect password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repo.save(user);

        return new ApiResponse("Updated password successfully");
    }

    @Override
    public ApiResponse delete(int userId) {
        repo.deleteById(userId);
        return new ApiResponse("Deleted user successfully");
    }

}
