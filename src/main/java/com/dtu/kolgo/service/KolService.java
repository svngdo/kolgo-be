package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.campaign.CampaignDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.kol.KolDetailsDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.model.Kol;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface KolService {

    void save(Kol kol);

    List<KolDto> getDtos();

    List<KolDto> getDtosByFieldIds(List<Short> fieldIds);

    KolDetailsDto getDetailsById(int id);

    KolDto getDtoByPrincipal(Principal principal);

    ApiResponse updateByPrincipal(Principal principal, KolDto dto);

    List<String> updateImages(Principal principal, List<MultipartFile> images);

    List<BookingDto> getBookingsById(int id);

    BookingDto createBooking(Principal principal, int id, BookingDto bookingDto);

    CampaignDto joinCampaign(Principal principal, int campaignId);

    CampaignDto quitCampaign(Principal principal, int campaignId);

    KolDto updateById(int id, KolDto dto);
}
