package com.dtu.kolgo.dto.booking;

import com.dtu.kolgo.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookingCreateDto {

    private String date;
    private BigDecimal postPrice;
    private Integer postNumber;
    private BigDecimal videoPrice;
    private Integer videoNumber;
    private BigDecimal totalPrice;
    private BookingStatus status;
    private Integer kolId;
}
