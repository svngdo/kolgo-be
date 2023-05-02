package com.dtu.kolgo.dto.request;

import com.dtu.kolgo.enums.BookingStatus;
import lombok.Data;

@Data
public class BookingRequest {

    private String date;
    private BookingStatus status;

}
