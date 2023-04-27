package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.request.CampaignRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.CampaignResponse;
import com.dtu.kolgo.model.Campaign;
import com.dtu.kolgo.model.Enterprise;

import java.security.Principal;
import java.util.List;

public interface CampaignService {

    ApiResponse save(Enterprise ent, CampaignRequest request);

    ApiResponse save(int entId, CampaignRequest request);

    ApiResponse save(Principal principal, CampaignRequest request);

    List<CampaignResponse> getAll();

    List<CampaignResponse> getAll(Principal principal, boolean isEnt);

    Campaign getById(int id);

    CampaignResponse getResponse(int id);

    ApiResponse update(int id, CampaignRequest request);

    ApiResponse update(Principal principal, int id, CampaignRequest request);

    ApiResponse delete(int id);

    ApiResponse delete(Principal principal, int id);

    CampaignResponse mapEntityToDto(Campaign campaign);

    List<CampaignResponse> mapEntitiesToDtos(List<Campaign> campaigns);

}
