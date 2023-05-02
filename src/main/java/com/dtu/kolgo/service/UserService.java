package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.EmailRequest;
import com.dtu.kolgo.dto.request.PasswordUpdateRequest;
import com.dtu.kolgo.dto.request.UserRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface UserService {

    ApiResponse save(User user);

    List<UserResponse> getAllResponses();

    User get(int userId);

    User get(String email);

    User get(Principal principal);

    UserResponse getResponse(int userId);

    String getRole(int id);

    String getRole(Principal principal);

    ApiResponse update(int userId, UserRequest request);

    ApiResponse updateAvatar(User user, MultipartFile avatar);

    ApiResponse updateEmail(Principal principal, EmailRequest request);

    ApiResponse updatePassword(Principal principal, PasswordUpdateRequest request);

    ApiResponse delete(int userId);

    UserResponse mapEntityToDto(User user);

    List<UserResponse> mapEntityToDto(List<User> users);

}
