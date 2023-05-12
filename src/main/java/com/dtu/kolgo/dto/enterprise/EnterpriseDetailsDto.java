package com.dtu.kolgo.dto.enterprise;

import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.model.Address;
import com.dtu.kolgo.model.Field;
import lombok.Data;

import java.util.List;

@Data
public class EnterpriseDetailsDto {

    private UserDto user;
    private String name;
    private String taxId;
    private Address address;
    private String phone;
    private List<Field> fields;
    private List<CampaignDto> campaigns;

}
