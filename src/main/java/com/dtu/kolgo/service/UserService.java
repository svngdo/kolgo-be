package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.UpdateUserRequest;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    void save(User user);

    List<UserResponse> getAll();

    User getById(int userId);

    User getByEmail(String email);

    UserResponse getResponseById(int userId);

    WebResponse update(int userId, UpdateUserRequest request);

    void updateAvatar(User user, MultipartFile avatar);

    WebResponse delete(int userId);

    void validateEmail(String email);

}
