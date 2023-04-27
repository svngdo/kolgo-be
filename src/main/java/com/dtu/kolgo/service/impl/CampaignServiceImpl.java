package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.request.CampaignRequest;
import com.dtu.kolgo.dto.response.ApiResponse;
import com.dtu.kolgo.dto.response.CampaignResponse;
import com.dtu.kolgo.dto.response.KolResponse;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Campaign;
import com.dtu.kolgo.model.Enterprise;
import com.dtu.kolgo.model.Kol;
import com.dtu.kolgo.repository.CampaignRepository;
import com.dtu.kolgo.service.CampaignService;
import com.dtu.kolgo.service.EnterpriseService;
import com.dtu.kolgo.service.KolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository repo;
    private final KolService kolService;
    private final EnterpriseService entService;

    @Override
    public ApiResponse save(Enterprise ent, CampaignRequest request) {
        repo.save(Campaign.builder()
                .cost(request.getCost())
                .description(request.getDescription())
                .status(request.getStatus())
                .createdAt(request.getTimestamp())
                .enterprise(ent)
                .kols(request.getKols() == null ? new ArrayList<>() : request.getKols())
                .build());
        return new ApiResponse("Saved campaign successfully");
    }

    @Override
    public ApiResponse save(int entId, CampaignRequest request) {
        Enterprise ent = entService.getById(entId);
        return save(ent, request);
    }

    @Override
    public ApiResponse save(Principal principal, CampaignRequest request) {
        Enterprise ent = entService.getByPrincipal(principal);
        return save(ent, request);
    }

    @Override
    public List<CampaignResponse> getAll() {
        return repo.findAll().stream()
                .map(c -> CampaignResponse.builder()
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CampaignResponse> getAll(Principal principal, boolean isEnt) {
        if (isEnt) {
            Enterprise ent = entService.getByPrincipal(principal);
            return mapEntitiesToDtos(repo.findAllByEnterprise(ent));
        }
        Kol kol = kolService.get(principal);
        return mapEntitiesToDtos(repo.findAllByKolsContains(kol));
    }

    @Override
    public Campaign getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Campaign with ID: " + id));
    }

    @Override
    public CampaignResponse getResponse(int id) {
        Campaign campaign = getById(id);
        List<KolResponse> kols = new ArrayList<>();
        if (campaign.getKols() != null) {
            kols = campaign.getKols().stream()
                    .map(k -> KolResponse.builder()
                            .kolId(k.getId())
                            .userId(k.getUser().getId())
                            .avatar(k.getUser().getAvatar())
                            .firstName(k.getUser().getFirstName())
                            .lastName(k.getUser().getLastName())
                            .email(k.getUser().getEmail())
                            .phoneNumber(k.getUser().getPhoneNumber())
//                            .genderId(k.getGender().getId())
//                            .cityId(k.getCity().getId())
//                            .kolFieldId(k.getField().getId())
//                            .facebookUrl(k.getFacebookUrl())
//                            .instagramUrl(k.getInstagramUrl())
//                            .tiktokUrl(k.getTiktokUrl())
//                            .youtubeUrl(k.getYoutubeUrl())
//                            .images(k.getImages().stream()
//                                    .map(img -> new ImageResponse(img.getName()))
//                                    .collect(Collectors.toList()))
                            .build())
                    .collect(Collectors.toList());
        }
        CampaignResponse res = mapEntityToDto(campaign);
        res.setKols(kols);
        return res;
    }

    @Override
    public ApiResponse update(int id, CampaignRequest request) {
        Campaign campaign = getById(id);
        campaign.setCost(request.getCost());
        campaign.setDescription(request.getDescription());
        campaign.setStatus(request.getStatus());
        return new ApiResponse("Updated campaign successfully");
    }

    @Override
    public ApiResponse update(Principal principal, int id, CampaignRequest request) {
        Enterprise ent = entService.getByPrincipal(principal);
        boolean hasCampaign = ent.getCampaigns().stream().anyMatch(c -> c.getId() == id);
        if (hasCampaign) {
            return update(id, request);
        }
        throw new NotFoundException("Incorrect Campaign with ID: " + id);
    }

    @Override
    public ApiResponse delete(int id) {
        repo.deleteById(id);
        return new ApiResponse("Deleted campaign successfully");
    }

    @Override
    public ApiResponse delete(Principal principal, int id) {
        Enterprise ent = entService.getByPrincipal(principal);
        boolean hasCampaign = ent.getCampaigns().stream().anyMatch(c -> c.getId() == id);
        if (hasCampaign) {
            return delete(id);
        }
        throw new NotFoundException("Incorrect Campaign with ID: " + id);
    }

    @Override
    public CampaignResponse mapEntityToDto(Campaign campaign) {
        return CampaignResponse.builder()
                .id(campaign.getId())
                .entId(campaign.getEnterprise().getId())
                .cost(campaign.getCost())
                .description(campaign.getDescription())
                .status(campaign.getStatus())
                .createdAt(campaign.getCreatedAt())
                .build();
    }

    @Override
    public List<CampaignResponse> mapEntitiesToDtos(List<Campaign> campaigns) {
        if (campaigns == null) {
            return new ArrayList<>();
        }
        return campaigns.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

}
