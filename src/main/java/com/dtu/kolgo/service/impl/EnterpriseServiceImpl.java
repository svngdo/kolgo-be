package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.EntUpdateRequest;
import com.dtu.kolgo.dto.response.EntResponse;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.City;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.EnterpriseField;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.service.CityService;
import com.dtu.kolgo.service.EnterpriseFieldService;
import com.dtu.kolgo.service.EnterpriseService;
import com.dtu.kolgo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public void save(Enterprise ent) {
        repo.save(ent);
    }

    @Override
    public List<EntResponse> getAll() {
        return repo.findAll().stream()
                .map(ent -> EntResponse.builder()
                        .userId(ent.getUser().getId())
                        .enterpriseId(ent.getId())
                        .firstName(ent.getUser().getFirstName())
                        .lastName(ent.getUser().getLastName())
                        .avatar(ent.getUser().getAvatar())
                        .email(ent.getUser().getEmail())
                        .name(ent.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Enterprise getById(int entId) {
        return repo.findById(entId)
                .orElseThrow(() -> new NotFoundException("Enterprise ID not found: " + entId));
    }

    @Override
    public Enterprise getByUser(User user) {
        return repo.findByUser(user)
                .orElseThrow(() -> new NotFoundException("Enterprise not found with User ID: " + user.getId()));
    }

    @Override
    public EntResponse getProfileById(int entId) {
        Enterprise ent = getById(entId);

        String addressDetails = null;
        Short cityId = null;

        if (ent.getAddress() != null) {
            addressDetails = ent.getAddress().getDetails() == null ? null : ent.getAddress().getDetails();
            cityId = ent.getAddress().getCity() == null ? null : ent.getAddress().getCity().getId();
        }

        return EntResponse.builder()
                .enterpriseId(entId)
                .firstName(ent.getUser().getFirstName())
                .lastName(ent.getUser().getLastName())
                .email(ent.getUser().getEmail())
                .phoneNumber(ent.getUser().getPhoneNumber() == null ? null : ent.getUser().getPhoneNumber())
                .avatar(ent.getUser().getAvatar() == null ? null : ent.getUser().getAvatar())
                .name(ent.getName())
                .enterpriseFieldId(ent.getField() == null ? null : ent.getField().getId())
                .addressDetails(addressDetails)
                .cityId(cityId)
                .taxIdentificationNumber(ent.getTaxIdentificationNumber())
                .build();
    }

    @Override
    public ApiResponse update(int entId, EntUpdateRequest request, MultipartFile avatar) {
        Enterprise ent = getById(entId);
        City city = cityService.getById(request.getCityId());
        EnterpriseField field = entFieldService.getById(request.getEnterpriseFieldId());
        userService.updateAvatar(ent.getUser(), avatar);

        ent.getUser().setFirstName(request.getFirstName());
        ent.getUser().setLastName(request.getLastName());
        ent.getUser().setPhoneNumber(request.getPhoneNumber());
        ent.setName(request.getName());
        ent.setField(field);
        ent.setTaxIdentificationNumber(request.getTaxIdentificationNumber());
//        ent.getAddress().setBuildingNumber(request.getBuildingNumber());
//        ent.getAddress().setStreetName(request.getStreetName());
//        ent.getAddress().setWard(request.getWard());
//        ent.getAddress().setDistrict(request.getDistrict());
        ent.getAddress().setDetails(request.getAddressDetails());
        ent.getAddress().setCity(city);

        repo.save(ent);

        return new ApiResponse("Updated successfully Enterprise with ID " + entId);
    }

    @Override
    public ApiResponse delete(int entId) {
        repo.deleteById(entId);
        return new ApiResponse("Deleted successfully Enterprise with ID " + entId);
    }

}
