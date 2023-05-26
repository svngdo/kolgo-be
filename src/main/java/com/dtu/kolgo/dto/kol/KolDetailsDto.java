package com.dtu.kolgo.dto.kol;

import com.dtu.kolgo.dto.campaign.CampaignDto;
import com.dtu.kolgo.dto.booking.BookingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class KolDetailsDto {

    private KolDto kol;
    private String facebookFollowersCount;
    private String instagramFollowersCount;
    private String tiktokFollowersCount;
    private String youtubeSubscribersCount;
    private List<BookingDto> bookings;
    private List<CampaignDto> campaigns;

}
