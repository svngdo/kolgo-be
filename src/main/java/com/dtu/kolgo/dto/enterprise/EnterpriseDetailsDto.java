package com.dtu.kolgo.dto.enterprise;

import com.dtu.kolgo.dto.CampaignDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnterpriseDetailsDto {

    private EnterpriseDto enterprise;
    private List<CampaignDto> campaigns;

}
