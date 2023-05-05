package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.enterprise.EntDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDetailsDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseProfileDto;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;

import java.security.Principal;
import java.util.List;

public interface EnterpriseService {

    ApiResponse save(Enterprise ent);

    List<EntDto> getAllDto();

    List<EntDto> getAllDtoByFieldId(short fieldId);

    Enterprise getById(int id);

    Enterprise getByUser(User user);

    Enterprise getByPrincipal(Principal principal);

    EnterpriseDetailsDto getDetailsById(int id);

    EnterpriseProfileDto getProfileByPrincipal(Principal principal);

    ApiResponse updateProfileByPrincipal(Principal principal, EnterpriseProfileDto profile);

    ApiResponse deleteById(int id);

}
