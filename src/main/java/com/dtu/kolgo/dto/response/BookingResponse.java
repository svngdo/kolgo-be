package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "endId",
        "entFirstName",
        "entLastName",
        "entName",
        "kolId",
        "kolFirstName",
        "kolLastName",
        "date",
        "cost"
})
public class BookingResponse {

    private int id;
    private LocalDate date;
    private int entId;
    private String entFirstName;
    private String entLastName;
    private String entName;
    private int kolId;
    private String kolFirstName;
    private String kolLastName;
    private BigDecimal paymentAmountPaid;
    private PaymentStatus paymentStatus;
    private List<FeedbackResponse> feedbacks;

}
