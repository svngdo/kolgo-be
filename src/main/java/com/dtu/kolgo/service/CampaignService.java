package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.model.Campaign;
import com.dtu.kolgo.model.Enterprise;

import java.util.List;

public interface CampaignService {

    Campaign save(Campaign campaign);

    List<Campaign> getAll();

    List<CampaignDto> getAllDto();

    Campaign getById(int id);

    CampaignDto getDtoById(int id);

    ApiResponse update(int id, CampaignDto dto);

    ApiResponse delete(int id);

    List<CampaignDto> getDtosByEnterprise(Enterprise enterprise);
}
