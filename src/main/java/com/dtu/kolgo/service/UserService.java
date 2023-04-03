package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.ChangePasswordRequest;
import com.dtu.kolgo.dto.request.ResetPasswordRequest;
import com.dtu.kolgo.dto.request.UpdatePasswordRequest;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.model.User;

import java.security.Principal;

public interface UserService {

    User save(User user);

    User fetch(long id);

    User fetch(String email);

    WebResponse resetPassword(ResetPasswordRequest request);

    WebResponse updatePassword(String resetPasswordToken, UpdatePasswordRequest request);

    WebResponse changePassword(Principal principal, ChangePasswordRequest request);

}
