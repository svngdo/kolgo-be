package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.UserUpdateRequest;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void save(User user);

    List<UserResponse> getAll();

    User getById(int userId);

    User getByEmail(String email);

    UserResponse getResponseById(int userId);

    ApiResponse update(int userId, UserUpdateRequest request);

    void updateAvatar(User user, MultipartFile avatar);

    ApiResponse delete(int userId);

    void validateEmail(String email);

}
