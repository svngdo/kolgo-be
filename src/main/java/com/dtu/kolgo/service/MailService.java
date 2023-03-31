package com.dtu.kolgo.service;

import com.dtu.kolgo.model.MailDetails;

public interface MailService {

    void sendSimpleMail(MailDetails details);

    String sendMailWithAttachment(MailDetails details);

}
