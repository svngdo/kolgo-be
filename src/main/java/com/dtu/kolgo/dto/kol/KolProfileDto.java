package com.dtu.kolgo.dto.kol;

import com.dtu.kolgo.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KolProfileDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String phone;
    @NotNull
    private Gender gender;
    @NotNull
    private Short cityId;
    private String addressDetails;
    @NotNull
    private Short fieldId;
    private BigDecimal postPrice;
    private BigDecimal videoPrice;
    private String facebookUrl;
    private String instagramUrl;
    private String tiktokUrl;
    private String youtubeUrl;

}
