package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.UpdateEnterpriseRequest;
import com.dtu.kolgo.dto.response.EnterpriseResponse;
import com.dtu.kolgo.dto.response.WebResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.User;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.service.CityService;
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

    @Override
    public void save(Enterprise ent) {
        repo.save(ent);
    }

    @Override
    public List<EnterpriseResponse> getAll() {
        return repo.findAll().stream()
                .map(ent -> EnterpriseResponse.builder()
                        .id(ent.getId())
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
                .orElseThrow(() -> new NotFoundException("Enterprise with User ID not found: " + user.getId()));
    }

    @Override
    public EnterpriseResponse getResponseById(int entId) {
        Enterprise ent = getById(entId);

        return EnterpriseResponse.builder()
                .id(ent.getId())
                .firstName(ent.getUser().getFirstName())
                .lastName(ent.getUser().getLastName())
                .avatar(ent.getUser().getAvatar())
                .email(ent.getUser().getEmail())
                .name(ent.getName())
                .phoneNumber(ent.getPhoneNumber())
                .taxIdentificationNumber(ent.getTaxIdentificationNumber())
                .address(ent.getAddress())
                .bookings(ent.getBookings())
                .payments(ent.getPayments())
                .campaigns(ent.getCampaigns())
                .feedbacks(ent.getFeedbacks())
                .build();
    }

    @Override
    public WebResponse update(int entId, UpdateEnterpriseRequest request) {
        Enterprise ent = getById(entId);

        ent.getUser().setFirstName(request.getFirstName());
        ent.getUser().setLastName(request.getLastName());
        ent.setName(request.getName());
        ent.setPhoneNumber(request.getPhoneNumber());
        ent.setTaxIdentificationNumber(request.getTaxIdentificationNumber());
        ent.getAddress().setBuildingNumber(request.getBuildingNumber());
        ent.getAddress().setStreetName(request.getStreetName());
        ent.getAddress().setWard(request.getWard());
        ent.getAddress().setDistrict(request.getDistrict());
        ent.getAddress().setCity(cityService.getById(request.getCityId()));

        repo.save(ent);

        return new WebResponse("Updated successfully Enterprise with ID " + entId);
    }

    @Override
    public WebResponse delete(int entId) {
        repo.deleteById(entId);
        return new WebResponse("Deleted successfully Enterprise with ID " + entId);
    }

}
