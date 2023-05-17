package com.dtu.kolgo.dto.kol;

import com.dtu.kolgo.dto.CampaignDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class KolDetailsDto {

    private KolDto kol;
    private List<String> images;
    private List<BookingDto> bookings;
    private List<CampaignDto> campaigns;

}
