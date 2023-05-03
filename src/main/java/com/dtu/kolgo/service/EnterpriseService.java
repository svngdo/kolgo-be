package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.enterprise.EntDetailsDto;
import com.dtu.kolgo.dto.enterprise.EntDto;
import com.dtu.kolgo.dto.enterprise.EntProfileDto;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface EnterpriseService {

    ApiResponse save(Enterprise ent);

    List<EntDto> getAllDto();

    List<EntDto> getAllDtoByField(short fieldId);

    Enterprise get(int id);

    Enterprise get(User user);

    Enterprise get(Principal principal);

    EntDetailsDto getDetails(int id);

    EntDto getDto(Principal principal);

    ApiResponse update(int id, EntProfileDto dto, MultipartFile avatar);

    ApiResponse update(Principal principal, EntProfileDto dto, MultipartFile avatar);

    ApiResponse delete(int id);

}
