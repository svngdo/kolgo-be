package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.enterprise.EntDetailsDto;
import com.dtu.kolgo.dto.enterprise.EntDto;
import com.dtu.kolgo.dto.enterprise.EntProfileDto;
import com.dtu.kolgo.enums.FieldType;
import com.dtu.kolgo.exception.InvalidException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.Field;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public List<EntDto> getAllDtoByField(short fieldId) {
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
    public Enterprise get(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Enterprise ID not found: " + id));
    }

    @Override
    public Enterprise get(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Enterprise not found with User ID: " + user.getId()));
    }

    @Override
    public Enterprise get(Principal principal) {
        User user = userService.get(principal);
        return get(user);
    }

    @Override
    public EntDetailsDto getDetails(int id) {
        Enterprise ent = get(id);
        return mapper.map(ent, EntDetailsDto.class);
    }

    @Override
    public EntDto getDto(Principal principal) {
        Enterprise ent = get(principal);
        return mapper.map(ent, EntDto.class);
    }

    @Override
    public ApiResponse update(int id, EntProfileDto dto, MultipartFile avatar) {
        Enterprise ent = get(id);

        userService.updateAvatar(ent.getUser(), avatar);

        ent.getUser().setFirstName(dto.getFirstName());
        ent.getUser().setLastName(dto.getLastName());
        ent.setName(dto.getName());
        ent.setPhone(dto.getPhone());
        ent.setTaxId(dto.getTaxId());
        ent.getAddress().setCity(cityService.get(dto.getCityId()));
        ent.getAddress().setDetails(dto.getAddressDetails());
        ent.setField(fieldService.get(dto.getFieldId()));

        repo.save(ent);

        return new ApiResponse("Updated successfully Enterprise with ID " + id);
    }

    @Override
    public ApiResponse update(
            Principal principal, EntProfileDto dto, MultipartFile avatar) {
        Enterprise ent = get(principal);
        return update(ent.getId(), dto, avatar);
    }

    @Override
    public ApiResponse delete(int id) {
        repo.deleteById(id);
        return new ApiResponse("Deleted successfully Enterprise with ID " + id);
    }

}
