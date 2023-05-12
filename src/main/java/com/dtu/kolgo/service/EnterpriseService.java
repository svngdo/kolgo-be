package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseProfileDto;
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

    EnterpriseDto getDtoById(int id);

    EnterpriseProfileDto getProfileByPrincipal(Principal principal);

    ApiResponse updateProfileByPrincipal(Principal principal, EnterpriseProfileDto profile);

    ApiResponse deleteById(int id);

}
