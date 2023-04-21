package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.model.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UserUpdateRequest {

    private String email;
    private List<Role> roles;
    private String firstName;
    private String lastName;
    private MultipartFile avatar;

}
