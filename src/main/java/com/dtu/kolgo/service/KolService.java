package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.ApiResponse;
import com.dtu.kolgo.dto.booking.BookingDto;
import com.dtu.kolgo.dto.kol.KolDetailsDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.kol.KolProfileDto;
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

    Map<String, Object> getProfileByPrincipal(Principal principal);

    ApiResponse updateProfileByPrincipal(Principal principal, KolProfileDto dto);

    Map<String, Object> updateImages(Principal principal, List<MultipartFile> images);

    List<BookingDto> getBookingsById(int id);

    BookingDto createBooking(Principal principal, int id, BookingDto bookingDto);

}
