package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.ChangePasswordRequest;
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

    WebResponse changePassword(Principal principal, ChangePasswordRequest request);

    WebResponse update(int userId, UserUpdateRequest request);

    WebResponse delete(int userId);
}
