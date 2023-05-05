package com.dtu.kolgo.controller;

import com.dtu.kolgo.dto.VnPayDto;
import com.dtu.kolgo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping("payments")
    public ResponseEntity<?> save(
            @RequestBody VnPayDto request
    ) {
       return new ResponseEntity<>(
               service.save(request),
               HttpStatus.OK
       );
    }

    @GetMapping("payments")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                service.getAll(),
                HttpStatus.OK
        );
    }

//    @GetMapping("payments/history")
//    public ResponseEntity<?> getAll(
//            Principal principal
//    ) {
//        return new ResponseEntity<>(
//                service.getAllResponses(principal),
//                HttpStatus.OK
//        );
//    }

}
