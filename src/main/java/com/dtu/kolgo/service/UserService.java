package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.EmailDto;
import com.dtu.kolgo.dto.PasswordUpdateDTO;
import com.dtu.kolgo.dto.UserDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface UserService {

    ApiResponse save(User user);

    List<UserDto> getAllDto();

    User get(int id);

    User get(String email);

    User get(Principal principal);

    UserDto getDto(int id);

    ApiResponse update(int id, UserDto dto, MultipartFile avatar);

    ApiResponse update(User user, UserDto dto, MultipartFile avatar);

    void updateAvatar(User user, MultipartFile avatar);

    ApiResponse updateEmail(Principal principal, EmailDto dto);

    ApiResponse updatePassword(Principal principal, PasswordUpdateDTO request);

    ApiResponse delete(int userId);

}
