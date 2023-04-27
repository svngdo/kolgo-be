package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.enums.CampaignStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "cost",
        "description",
        "status",
        "timestamp",
        "userId",
        "entId",
        "kols"
})
public class CampaignResponse {

    private Integer id;
    private Integer cost;
    private String description;
    private CampaignStatus status;
    private LocalDateTime createdAt;
//    private Integer userId;
    private Integer entId;
    private List<KolResponse> kols;

}
