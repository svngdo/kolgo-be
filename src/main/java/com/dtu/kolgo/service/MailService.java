package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.MailDto;

public interface MailService {

    void send(MailDto details, boolean isHtml);

    String sendMailWithAttachment(MailDto details);

}
