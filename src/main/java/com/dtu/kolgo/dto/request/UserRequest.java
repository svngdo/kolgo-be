package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.model.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private MultipartFile avatar;
    private Role role;

}
