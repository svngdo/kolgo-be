package com.dtu.kolgo.dto.booking;

import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.payment.PaymentDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

    private Integer id;
    private BigDecimal postPrice;
    private Integer postNumber;
    private BigDecimal videoPrice;
    private Integer videoNumber;
    private BigDecimal totalPrice;
    private String description;
    private String timestamp;
    private BookingStatus status;
    private UserDto user;
    private KolDto kol;
    private PaymentDto payment;

}
