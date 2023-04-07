package com.dtu.kolgo.dto.response;

import com.dtu.kolgo.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "firstName",
        "lastName",
        "avatar",
        "email",
        "name",
        "phoneNumber",
        "taxIdentificationNumber",
        "address",
        "bookings",
        "payments",
        "campaigns",
        "feedbacks",
})
public class EnterpriseResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String avatar;
    private String email;
    private String name;
    private String phoneNumber;
    private String taxIdentificationNumber;
    private Address address;
    private List<Booking> bookings;
    private List<Payment> payments;
    private List<Campaign> campaigns;
    private List<Feedback> feedbacks;

}
