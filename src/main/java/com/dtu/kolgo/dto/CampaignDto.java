package com.dtu.kolgo.dto;

import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.enums.CampaignStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignDto {

    private Integer id;
    private Integer cost;
    private String description;
    private CampaignStatus status;
    private LocalDateTime createdAt;
    private Integer entId;
    private List<KolDto> kols;

}
