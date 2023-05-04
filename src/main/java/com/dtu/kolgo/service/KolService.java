package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.kol.KolDetailsDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.kol.KolProfileDto;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface KolService {

    void save(Kol kol);

    List<KolDto> getAllDto();

    List<KolDto> getAllDtoByField(short fieldId);

    Kol get(int id);

    Kol get(User user);

    Kol get(Principal principal);

    KolDetailsDto getDetails(Kol kol);

    KolDetailsDto getDetails(int id);

    KolDetailsDto getDetails(Principal principal);

    ApiResponse updateProfile(int id, KolProfileDto dto, MultipartFile avatar, List<MultipartFile> images);

    ApiResponse updateProfile(Principal principal, KolProfileDto dto, MultipartFile avatar, List<MultipartFile> images);

    void updateImages(Kol kol, List<MultipartFile> images);

    ApiResponse delete(int id);

}
