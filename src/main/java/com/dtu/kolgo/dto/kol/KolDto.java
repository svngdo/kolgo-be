package com.dtu.kolgo.dto.kol;

import com.dtu.kolgo.dto.UserDto;
import com.dtu.kolgo.enums.Gender;
import com.dtu.kolgo.model.Address;
import com.dtu.kolgo.model.Field;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KolDto {

    private UserDto user;
    private Integer id;
    private String phone;
    private Gender gender;
    private Address address;
    private Field field;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;

}
