package com.dtu.kolgo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MailDetails {

    private String recipient;
    private String subject;
    private String body;
    private String attachment;

}
