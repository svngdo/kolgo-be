package com.dtu.kolgo.dto.enterprise;

import com.dtu.kolgo.dto.CampaignDto;
import lombok.Data;

import java.util.List;

@Data
public class EnterpriseDetailsDto {

    private EnterpriseDto enterprise;
    private List<CampaignDto> campaigns;

}
