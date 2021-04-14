package com.example.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SendEmailDto;
import com.example.demo.jms.Sender;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final Sender sender;

    @PostMapping("/send-email")
    public ResponseEntity<Void> sendEmail(@RequestBody final SendEmailDto sendEmailDto) {
        sender.sendMessage(sendEmailDto);
        return ResponseEntity.noContent().build();
    }

}
