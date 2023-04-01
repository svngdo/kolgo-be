package com.dtu.kolgo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailDetails {

    private String recipient;
    private String subject;
    private String body;
    private String attachment;

}
