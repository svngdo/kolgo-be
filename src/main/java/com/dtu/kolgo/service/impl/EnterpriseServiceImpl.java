package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.response.EnterpriseResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.City;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.EnterpriseField;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.service.CityService;
import com.dtu.kolgo.service.EnterpriseFieldService;
import com.dtu.kolgo.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository repo;
    private final CityService cityService;
    private final EnterpriseFieldService entFieldService;

    @Override
    public void save(Enterprise ent) {
        repo.save(ent);
    }

    @Override
    public List<EnterpriseResponse> getAll() {
        return repo.findAll().stream()
                .map(ent -> EnterpriseResponse.builder()
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
    public EnterpriseResponse getProfileById(int entId) {
        Enterprise ent = getById(entId);

        String buildingNumber = null;
        String streetName = null;
        String ward = null;
        String district = null;
        Short cityId = null;

        if (ent.getAddress() != null) {
            buildingNumber = ent.getAddress().getBuildingNumber() == null ? null : ent.getAddress().getBuildingNumber();
            streetName = ent.getAddress().getStreetName() == null ? null : ent.getAddress().getStreetName();
            ward = ent.getAddress().getWard() == null ? null : ent.getAddress().getWard();
            district = ent.getAddress().getDistrict() == null ? null : ent.getAddress().getDistrict();
            cityId = ent.getAddress().getCity() == null ? null : ent.getAddress().getCity().getId();
        }

        return EnterpriseResponse.builder()
                .enterpriseId(entId)
                .firstName(ent.getUser().getFirstName())
                .lastName(ent.getUser().getLastName())
                .email(ent.getUser().getEmail())
                .phoneNumber(ent.getUser().getPhoneNumber() == null ? null : ent.getUser().getPhoneNumber())
                .avatar(ent.getUser().getAvatar() == null ? null : ent.getUser().getAvatar())
                .name(ent.getName())
                .enterpriseFieldId(ent.getField() == null ? null : ent.getField().getId())
                .buildingNumber(buildingNumber)
                .streetName(streetName)
                .ward(ward)
                .district(district)
                .cityId(cityId)
                .taxIdentificationNumber(ent.getTaxIdentificationNumber())
                .build();
    }

    @Override
    public WebResponse update(int entId, UpdateEnterpriseRequest request) {
        Enterprise ent = getById(entId);
        City city = cityService.getById(request.getCityId());
        EnterpriseField field = entFieldService.getById(request.getEnterpriseFieldId());

        ent.getUser().setFirstName(request.getFirstName());
        ent.getUser().setLastName(request.getLastName());
        ent.getUser().setPhoneNumber(request.getPhoneNumber());
        ent.setName(request.getName());
        ent.setField(field);
        ent.setTaxIdentificationNumber(request.getTaxIdentificationNumber());
        ent.getAddress().setBuildingNumber(request.getBuildingNumber());
        ent.getAddress().setStreetName(request.getStreetName());
        ent.getAddress().setWard(request.getWard());
        ent.getAddress().setDistrict(request.getDistrict());
        ent.getAddress().setCity(city);

        repo.save(ent);

        return new WebResponse("Updated successfully Enterprise with ID " + entId);
    }

    @Override
    public WebResponse delete(int entId) {
        repo.deleteById(entId);
        return new WebResponse("Deleted successfully Enterprise with ID " + entId);
    }

}
