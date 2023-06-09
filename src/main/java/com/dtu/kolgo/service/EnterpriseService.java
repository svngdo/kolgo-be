package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.campaign.CampaignCreateUpdateDto;
import com.dtu.kolgo.dto.campaign.CampaignDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDetailsDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface EnterpriseService {

    ApiResponse save(Enterprise ent);

    List<EnterpriseDto> getDtos();

    List<EnterpriseDto> getDtosByFieldIds(List<Short> fieldIds);

    Enterprise getById(int id);

    Enterprise getByUser(User user);

    Enterprise getByPrincipal(Principal principal);

    EnterpriseDetailsDto getDetailsById(int id);

    EnterpriseDto getDtoByPrincipal(Principal principal);

    ApiResponse updateByPrincipal(Principal principal, EnterpriseDto dto);

    ApiResponse deleteById(int id);

    CampaignDto createCampaign(Principal principal, CampaignCreateUpdateDto campaignCreateUpdateDto, List<MultipartFile> images);

    CampaignDto updateCampaign(Principal principal, int campaignId, CampaignCreateUpdateDto campaignCreateUpdateDto, List<MultipartFile> images);

    List<CampaignDto> getCampaignDtos(Principal principal);

    CampaignDto getCampaignDtoByPrincipal(Principal principal, int campaignId);

    ApiResponse deleteCampaign(Principal principal, int campaignId);

    List<String> updateCampaignsImages(Principal principal, int campaignId, List<MultipartFile> images);

    EnterpriseDto updateById(int id, EnterpriseDto dto);
}