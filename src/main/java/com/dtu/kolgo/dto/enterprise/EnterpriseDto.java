package com.dtu.kolgo.dto.enterprise;

import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnterpriseDto {

    private Integer userId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;
    private String avatar;
    private Role role;
    private Integer id;
    private String name;
    private String phone;
    private String taxId;
    @NotNull
    private Short cityId;
    private String cityName;
    private String addressDetails;
    @NotBlank
    private List<Short> fieldIds;
    private List<String> fieldNames;
    private List<CampaignDto> campaigns;

}
