package com.dtu.kolgo.dto.enterprise;

import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.model.Address;
import com.dtu.kolgo.model.Field;
import lombok.Data;

import java.util.List;

@Data
public class EntDetailsDto {

    private UserDto user;
    private Integer id;
    private String name;
    private String phone;
    private String taxId;
    private Address address;
    private Field field;
    private List<CampaignDto> campaigns;

}
