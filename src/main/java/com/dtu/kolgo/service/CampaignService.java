package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.model.Campaign;

import java.security.Principal;
import java.util.List;

public interface CampaignService {

    List<Campaign> getAll();

    List<CampaignDto> getAllDto();

    List<CampaignDto> getAllDtoEnt(Principal principal);

    Campaign getById(int id);

    CampaignDto getDtoById(int id);

    ApiResponse update(int id, CampaignDto dto);

    ApiResponse delete(int id);

}
