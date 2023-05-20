package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDetailsDto;
import com.dtu.kolgo.dto.enterprise.EnterpriseDto;
import com.dtu.kolgo.enums.CampaignStatus;
import com.dtu.kolgo.exception.AccessDeniedException;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.*;
import com.dtu.kolgo.repository.EnterpriseRepository;
import com.dtu.kolgo.service.*;
import com.dtu.kolgo.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
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
    private final CampaignService campaignService;

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
    public EnterpriseDetailsDto getDetailsById(int id) {
        Enterprise ent = getById(id);
        EnterpriseDto dto = mapper.map(ent, EnterpriseDto.class);
        List<CampaignDto> campaigns = ent.getCampaigns().stream().map(campaign -> mapper.map(campaign, CampaignDto.class)).toList();
        return new EnterpriseDetailsDto(dto, campaigns);
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
    public CampaignDto createCampaign(Principal principal, CampaignDto campaignDto) {
        User user = userService.getByPrincipal(principal);
        Enterprise ent = getByUser(user);

        String name = campaignDto.getName();
        List<Field> fields = campaignDto.getFieldIds().stream().map(fieldService::getById).toList();
        LocalDateTime timestamp = DateTimeUtils.convertToLocalDateTime(campaignDto.getTimestamp());
        LocalDateTime startTime = DateTimeUtils.convertToLocalDateTime(campaignDto.getStartTime());
        LocalDateTime finishTime = DateTimeUtils.convertToLocalDateTime(campaignDto.getFinishTime());
        String location = campaignDto.getLocation();
        String description = campaignDto.getDescription();
        String details = campaignDto.getDetails();
        CampaignStatus status = campaignDto.getStatus();

        Campaign campaign = campaignService.save(new Campaign(
                name,
                fields,
                timestamp,
                startTime,
                finishTime,
                location,
                description,
                details,
                status,
                ent,
                new ArrayList<>(),
                new ArrayList<>()
        ));

        if (ent.getCampaigns() != null) {
            ent.getCampaigns().add(campaign);
        } else {
            ent.setCampaigns(new ArrayList<>() {{
                add(campaign);
            }});
        }

        repo.save(ent);
        return mapper.map(campaign, CampaignDto.class);
    }

    @Override
    public CampaignDto updateCampaign(Principal principal, int campaignId, CampaignDto campaignDto) {
        Enterprise ent = getByPrincipal(principal);
        Campaign campaign = campaignService.getById(campaignId);

        campaign.setName(campaignDto.getName());
        campaign.setFields(new ArrayList<>() {{
            campaignDto.getFieldIds().forEach(id -> {
                Field field = fieldService.getById(id);
                this.add(field);
            });
        }});
        campaign.setTimestamp(DateTimeUtils.convertToLocalDateTime(campaignDto.getTimestamp()));
        campaign.setLocation(campaignDto.getLocation());
        campaign.setDescription(campaignDto.getDescription());
        campaign.setDetails(campaignDto.getDetails());
        campaign.setStatus(campaignDto.getStatus());

        campaignService.save(campaign);

        return mapper.map(campaign, CampaignDto.class);
    }

    @Override
    public List<CampaignDto> getCampaignDtos(Principal principal) {
        Enterprise ent = getByPrincipal(principal);
        return campaignService.getDtosByEnterprise(ent);
    }

    @Override
    public CampaignDto getCampaignDtoByPrincipal(Principal principal, int campaignId) {
        Enterprise ent = getByPrincipal(principal);
        Campaign campaign = campaignService.getById(campaignId);
        if (!campaign.getEnterprise().equals(ent)) {
            throw new AccessDeniedException();
        }
        return mapper.map(campaign, CampaignDto.class);
    }

    @Override
    public ApiResponse deleteCampaign(Principal principal, int campaignId) {
        Enterprise ent = getByPrincipal(principal);
        Campaign campaign = campaignService.getById(campaignId);
        if (!campaign.getEnterprise().equals(ent)) {
            throw new AccessDeniedException();
        }
        campaignService.delete(campaignId);
        return new ApiResponse("Delete campaign successfully");
    }

}
