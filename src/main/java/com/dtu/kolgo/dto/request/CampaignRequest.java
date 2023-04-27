package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.enums.CampaignStatus;
import com.dtu.kolgo.model.Kol;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CampaignRequest {

    private Integer cost;
    private String description;
    private CampaignStatus status;
    private LocalDateTime timestamp;
    private List<Kol> kols;

}
