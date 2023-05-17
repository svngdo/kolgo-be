package com.dtu.kolgo.dto.booking;

import com.dtu.kolgo.dto.feedback.FeedbackDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDetailsDto {

    private BookingDto booking;
    private List<FeedbackDto> feedbacks;

}
