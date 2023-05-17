package com.dtu.kolgo.dto.booking;

import com.dtu.kolgo.dto.feedback.FeedbackDto;
import com.dtu.kolgo.dto.kol.KolDto;
import com.dtu.kolgo.dto.payment.PaymentDto;
import com.dtu.kolgo.dto.user.UserDto;
import com.dtu.kolgo.enums.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

    private Integer id;
    @NotNull
    private BigDecimal postPrice;
    @NotNull
    private Integer postNumber;
    @NotNull
    private BigDecimal videoPrice;
    @NotNull
    private Integer videoNumber;
    @NotNull
    private BigDecimal totalPrice;
    private String description;
    @NotBlank
    private String timestamp;
    private BookingStatus status;
    private UserDto user;
    private KolDto kol;
    private PaymentDto payment;
    private FeedbackDto feedback;

}
