package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.MailDetails;

public interface MailService {

    void send(MailDetails details, boolean isHtml);

    String sendMailWithAttachment(MailDetails details);

}
