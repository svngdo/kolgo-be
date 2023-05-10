package com.dtu.kolgo.service;

import com.dtu.kolgo.dto.message.MessageDto;

public interface MessageService {

    void handlePublicMessage(MessageDto dto);

    void handlePrivateMessage(MessageDto dto);

}
