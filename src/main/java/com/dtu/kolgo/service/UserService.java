package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.user.EmailDto;
import com.dtu.kolgo.dto.user.PasswordUpdateDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface UserService {

    ApiResponse save(User user);

    List<UserDto> getAllDto();

    User getById(int id);

    User getByEmail(String email);

    User getByPrincipal(Principal principal);

    UserDto getDtoById(int id);

    ApiResponse updateById(int id, UserDto dto);

    ApiResponse updateByUser(User user, UserDto dto);

    Map<String, Object> updateAvatar(Principal principal, MultipartFile avatar);

    ApiResponse updateEmail(Principal principal, EmailDto dto);

    ApiResponse updatePassword(Principal principal, PasswordUpdateDto request);

    ApiResponse deleteById(int userId);

}
