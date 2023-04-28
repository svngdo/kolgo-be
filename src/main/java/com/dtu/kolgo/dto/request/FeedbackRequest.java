package com.dtu.kolgo.dto.request;

import lombok.Data;

@Data
public class FeedbackRequest {

    private short rating;
    private String comment;

}
