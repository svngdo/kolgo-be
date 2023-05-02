package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.EntUpdateRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.EntResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository repo;
    private final CityService cityService;
    private final EnterpriseFieldService entFieldService;
    private final UserService userService;

    @Override
    public ApiResponse save(Enterprise ent) {
        repo.save(ent);
        return new ApiResponse("Saved Enterprise successfully");
    }

    @Override
    public List<EntResponse> getAllResponses() {
        return mapEntityToDto(repo.findAll());
    }

    @Override
    public List<EntResponse> getAllResponses(short fieldId) {
        EnterpriseField field = entFieldService.getById(fieldId);
        List<Enterprise> ents = repo.findAllByField(field);
        return mapEntityToDto(ents);
    }

    @Override
    public Enterprise get(int entId) {
        return repo.findById(entId)
                .orElseThrow(() -> new NotFoundException("Enterprise ID not found: " + entId));
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
    public EntResponse getProfile(int entId) {
        Enterprise ent = get(entId);
        return mapEntityToDto(ent);
    }

    @Override
    public EntResponse getProfile(Principal principal) {
        Enterprise ent = get(principal);
        return getProfile(ent.getId());
    }

    @Override
    public ApiResponse updateProfile(int entId, EntUpdateRequest request, MultipartFile avatar) {
        Enterprise ent = get(entId);
        City city = cityService.getById(request.getCityId());
        EnterpriseField field = entFieldService.getById(request.getEnterpriseFieldId());

        if (avatar != null) {
            userService.updateAvatar(ent.getUser(), avatar);
        }
        if (ent.getAddress() == null) {
            ent.setAddress(new Address());
        }

        ent.getUser().setFirstName(request.getFirstName());
        ent.getUser().setLastName(request.getLastName());
        ent.getUser().setPhone(request.getPhoneNumber());
        ent.setName(request.getName());
        ent.setField(field);
        ent.setTaxIdentificationNumber(request.getTaxIdentificationNumber());
        ent.getAddress().setDetails(request.getAddressDetails());
        ent.getAddress().setCity(city);

        repo.save(ent);

        return new ApiResponse("Updated successfully Enterprise with ID " + entId);
    }

    @Override
    public ApiResponse updateProfile(
            Principal principal, EntUpdateRequest request, MultipartFile avatar) {
        Enterprise ent = get(principal);
        return updateProfile(ent.getId(), request, avatar);
    }

    @Override
    public ApiResponse delete(int entId) {
        repo.deleteById(entId);
        return new ApiResponse("Deleted successfully Enterprise with ID " + entId);
    }

    @Override
    public EntResponse mapEntityToDto(Enterprise ent) {
        String addressDetails = null;
        Short cityId = null;

        if (ent.getAddress() != null) {
            addressDetails = ent.getAddress().getDetails() == null ? null : ent.getAddress().getDetails();
            cityId = ent.getAddress().getCity() == null ? null : ent.getAddress().getCity().getId();
        }

        return EntResponse.builder()
                .id(ent.getId())
                .firstName(ent.getUser().getFirstName())
                .lastName(ent.getUser().getLastName())
                .email(ent.getUser().getEmail())
                .phoneNumber(ent.getUser().getPhone() == null ? null : ent.getUser().getPhone())
                .avatar(ent.getUser().getAvatar() == null ? null : ent.getUser().getAvatar())
                .name(ent.getName())
                .enterpriseFieldId(ent.getField() == null ? null : ent.getField().getId())
                .addressDetails(addressDetails)
                .cityId(cityId)
                .taxIdentificationNumber(ent.getTaxIdentificationNumber())
                .build();
    }

    @Override
    public List<EntResponse> mapEntityToDto(List<Enterprise> enterprises) {
        return enterprises.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

}
