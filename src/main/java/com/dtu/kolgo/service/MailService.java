package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.MailDetails;

public interface MailService {

    void sendSimpleMail(MailDetails details);

    String sendMailWithAttachment(MailDetails details);

}
