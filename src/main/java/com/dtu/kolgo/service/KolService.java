package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.kol.KolDetailsDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.model.Kol;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface KolService {

    void save(Kol kol);

    List<KolDto> getDtos();

    List<KolDto> getDtosByFieldIds(List<Short> fieldIds);

    KolDetailsDto getDetailsById(int id);

    KolDto getDtoByPrincipal(Principal principal);

    ApiResponse updateByPrincipal(Principal principal, KolDto dto);

    Map<String, Object> updateImages(Principal principal, List<MultipartFile> images);

    List<BookingDto> getBookingsById(int id);

    BookingDto createBooking(Principal principal, int id, BookingDto bookingDto);

    CampaignDto joinCampaign(Principal principal, int campaignId);

    CampaignDto quitCampaign(Principal principal, int campaignId);
}
