package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.enums.PaymentMethod;
import com.dtu.kolgo.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "paymentId",
        "amount",
        "sender",
        "receiver"
})
public class PaymentResponse {

    private int id;
    private PaymentMethod method;
    private String amountPaid;
    private String description;
    private PaymentStatus status;
    private int userId;

}
