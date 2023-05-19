package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDetailsDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;

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

    CampaignDto createCampaign(Principal principal, CampaignDto campaignDto);

}