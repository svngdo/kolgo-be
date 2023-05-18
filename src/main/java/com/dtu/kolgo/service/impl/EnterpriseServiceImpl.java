package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.Field;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.service.CityService;
import com.dtu.kolgo.service.EnterpriseService;
import com.dtu.kolgo.service.FieldService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository repo;
    private final FieldService fieldService;
    private final UserService userService;
    private final ModelMapper mapper;
    private final CityService cityService;

    @Override
    public ApiResponse save(Enterprise ent) {
        repo.save(ent);
        return new ApiResponse("Saved Enterprise successfully");
    }

    @Override
    public List<EnterpriseDto> getDtos() {
        return repo.findAll().stream().map(ent -> mapper.map(ent, EnterpriseDto.class)).toList();
    }

    @Override
    public List<EnterpriseDto> getDtosByFieldIds(List<Short> fieldIds) {
        List<Field> fields = fieldIds.stream().map(fieldService::getById).toList();
        return repo.findAllByFieldsIn(fields).stream().map(ent -> mapper.map(ent, EnterpriseDto.class)).toList();
    }

    @Override
    public Enterprise getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Enterprise not found with ID: " + id));
    }

    @Override
    public Enterprise getByUser(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Enterprise not found with User ID: " + user.getId()));
    }

    @Override
    public Enterprise getByPrincipal(Principal principal) {
        User user = userService.getByPrincipal(principal);
        return getByUser(user);
    }

    @Override
    public EnterpriseDto getDtoById(int id) {
        Enterprise ent = getById(id);
        return mapper.map(ent, EnterpriseDto.class);
    }

    @Override
    public EnterpriseDto getDtoByPrincipal(Principal principal) {
        Enterprise ent = getByPrincipal(principal);
        return mapper.map(ent, EnterpriseDto.class);
    }

    @Override
    public ApiResponse updateByPrincipal(Principal principal, EnterpriseDto dto) {
        Enterprise ent = getByPrincipal(principal);

        ent.getUser().setFirstName(dto.getFirstName());
        ent.getUser().setLastName(dto.getLastName());
        ent.setName(dto.getName());
        ent.setPhone(dto.getPhone());
        ent.setTaxId(dto.getTaxId());
        ent.setCity(cityService.getById(dto.getCityId()));
        ent.setAddressDetails(dto.getAddressDetails());
        ent.setFields(new ArrayList<>() {{
            dto.getFieldIds().forEach(id -> this.add(fieldService.getById(id)));
        }});

        repo.save(ent);

        return new ApiResponse("Updated profile successfully");
    }

    @Override
    public ApiResponse deleteById(int id) {
        repo.deleteById(id);
        return new ApiResponse("Deleted enterprise successfully");
    }

    @Override
    public CampaignDto createCampaign(int id, CampaignDto campaignDto) {
        return null;
    }

}
