package com.dtu.kolgo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CampaignCreateDto {

    @NotBlank
    private String name;
    @NotNull
    private List<Short> fieldIds;
    @NotBlank
    private String timestamp;
    @NotBlank
    private String startTime;
    @NotBlank
    private String finishTime;
    @NotBlank
    private String location;
    private String description;
    private String details;
    private Integer entId;
//    private List<MultipartFile> images;

}
