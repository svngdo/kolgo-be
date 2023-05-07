package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.kol.KolProfileDto;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface KolService {

    void save(Kol kol);

    List<KolDto> getAllDto(Short fieldId);

    List<KolDto> getAllDtoByFieldId(short fieldId);

    Kol getById(int id);

    Kol getByUser(User user);

    Kol getByPrincipal(Principal principal);

    Map<String, Object> getDetailsById(int id);

    Map<String, Object> getProfileByPrincipal(Principal principal);

    ApiResponse updateProfileByPrincipal(Principal principal, KolProfileDto profile);

    Map<String, Object> updateImages(Principal principal, List<MultipartFile> images);

    ApiResponse deleteById(int id);

}
