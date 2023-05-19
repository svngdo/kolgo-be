package com.dtu.kolgo.service.impl;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.exception.NotFoundException;
import com.dtu.kolgo.model.Campaign;
import com.dtu.kolgo.repository.CampaignRepository;
import com.dtu.kolgo.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository repo;
    private final ModelMapper mapper;

    @Override
    public Campaign save(Campaign campaign) {
        return repo.save(campaign);
    }

    @Override
    public List<Campaign> getAll() {
        return repo.findAll();
    }

    @Override
    public List<CampaignDto> getAllDto() {
        return repo.findAll()
                .stream()
                .map(campaign -> mapper.map(campaign, CampaignDto.class))
                .toList();
    }

    @Override
    public Campaign getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found Campaign with ID: " + id));
    }

    @Override
    public CampaignDto getDtoById(int id) {
//        Campaign campaign = getById(id);
//        List<KolDto> kols = new ArrayList<>();
//        if (campaign.getKols() != null) {
//            kols = kolService.mapEntityToDto(campaign.getKols());
//        }
//        CampaignDto res = mapEntityToDto(campaign);
//        res.setKols(kols);
//        return res;
        return null;
    }

    @Override
    public ApiResponse update(int id, CampaignDto dto) {
//        Campaign campaign = getById(id);
//        campaign.setCost(dto.getCost());
//        campaign.setDescription(dto.getDescription());
//        campaign.setStatus(dto.getStatus());
        return new ApiResponse("Updated campaign successfully");
    }

    @Override
    public ApiResponse delete(int id) {
        repo.deleteById(id);
        return new ApiResponse("Deleted campaign successfully");
    }

}
