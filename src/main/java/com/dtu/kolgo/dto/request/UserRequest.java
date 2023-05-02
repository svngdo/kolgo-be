package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.enums.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserRequest {

    private String email;
    private Role role;
    private String firstName;
    private String lastName;
    private MultipartFile avatar;

}
