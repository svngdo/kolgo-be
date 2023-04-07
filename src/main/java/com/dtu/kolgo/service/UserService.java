package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.UserEmailRequest;
import com.dtu.kolgo.dto.request.UserPasswordRequest;
import com.dtu.kolgo.dto.request.UserProfileRequest;
import com.dtu.kolgo.dto.request.UserUpdateRequest;
import com.dtu.kolgo.dto.response.UserResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.User;

import java.security.Principal;
import java.util.List;

public interface UserService {

    void save(User user);

    List<UserResponse> getAll();

    User getById(int userId);

    UserResponse getResponseById(int userId);

    User getByEmail(String email);

    WebResponse update(int userId, UserUpdateRequest request);

    WebResponse updateEmail(Principal principal, UserEmailRequest request);

    WebResponse updatePassword(Principal principal, UserPasswordRequest request);

    WebResponse updateProfile(Principal principal, UserProfileRequest request);

    WebResponse delete(int userId);

}
