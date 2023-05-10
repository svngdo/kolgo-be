package com.dtu.kolgo.controller;

import com.dtu.kolgo.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService service;

    @GetMapping("chats/{id}/messages")
    public ResponseEntity<?> getMessages(
            @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(
                service.getAllByChatId(id),
                HttpStatus.OK
        );
    }

}
