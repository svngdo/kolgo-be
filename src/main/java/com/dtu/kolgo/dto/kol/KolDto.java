package com.dtu.kolgo.dto.kol;

import com.dtu.kolgo.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KolDto {

    private Integer userId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;
    private String avatar;
    private String role;
    private Integer id;
    private String phone;
    @NotNull
    private Gender gender;
    private Short cityId;
    private String cityName;
    private String addressDetails;
    @NotNull
    private List<Short> fieldIds;
    private List<String> fieldNames;
    private BigDecimal postPrice;
    private BigDecimal videoPrice;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;

}
