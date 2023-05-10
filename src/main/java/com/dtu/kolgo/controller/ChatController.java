package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.message.ChatDto;
import com.dtu.kolgo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService service;

    @GetMapping("chats")
    public ResponseEntity<?> getChats(
            Principal principal
    ) {
        return new ResponseEntity<>(
                service.getAllDetailsByPrincipal(principal),
                HttpStatus.OK
        );
    }

    @PostMapping("chats")
    public ResponseEntity<?> postChat(
            Principal principal,
            @RequestBody ChatDto dto
    ) {
        System.out.println("creating new chat");
        return new ResponseEntity<>(
                service.save(principal, dto),
                HttpStatus.OK
        );
    }

}
