package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.enterprise.EntDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDetailsDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseProfileDto;
import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.exception.InvalidException;
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
    public List<EntDto> getAllDto() {
        return repo.findAll()
                .stream()
                .map(ent -> mapper.map(ent, EntDto.class))
                .toList();
    }

    @Override
    public List<EntDto> getAllDtoByFieldId(short fieldId) {
        Field field = fieldService.get(fieldId);
        if (field.getType() != FieldType.ENTERPRISE) {
            throw new InvalidException("Invalid field type");
        }
        return repo.findAllByField(field)
                .stream()
                .map(ent -> mapper.map(ent, EntDto.class))
                .toList();
    }

    @Override
    public Enterprise getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Enterprise ID not found: " + id));
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
    public EnterpriseDetailsDto getDetailsById(int id) {
        Enterprise ent = getById(id);
        return null;
    }

    @Override
    public EnterpriseProfileDto getProfileByPrincipal(Principal principal) {
        Enterprise ent = getByPrincipal(principal);
        return mapper.map(ent, EnterpriseProfileDto.class);
    }

    @Override
    public ApiResponse updateProfileByPrincipal(Principal principal, EnterpriseProfileDto profile) {
        Enterprise ent = getByPrincipal(principal);

        ent.getUser().setFirstName(profile.getFirstName());
        ent.getUser().setLastName(profile.getLastName());
        ent.setName(profile.getName());
        ent.setPhone(profile.getPhone());
        ent.setTaxId(profile.getTaxId());
        ent.getAddress().setCity(cityService.get(profile.getCityId()));
        ent.getAddress().setDetails(profile.getAddressDetails());
        ent.setField(fieldService.get(profile.getFieldId()));

        repo.save(ent);

        return new ApiResponse("Updated profile successfully");
    }

    @Override
    public ApiResponse deleteById(int id) {
        repo.deleteById(id);
        return new ApiResponse("Deleted enterprise successfully");
    }

}
