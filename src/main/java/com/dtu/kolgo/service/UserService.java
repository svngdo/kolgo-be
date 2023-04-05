package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.ChangePasswordRequest;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.User;

import java.security.Principal;

public interface UserService {

    void save(User user);

    User getById(long id);

    User getByEmail(String email);

    WebResponse changePassword(Principal principal, ChangePasswordRequest request);

}
