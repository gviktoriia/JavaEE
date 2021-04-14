package com.example.demo.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.dto.SendEmailDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SendEmailReceiver {

    @JmsListener(destination = "${notification.queue}")
    void receiveEmail(SendEmailDto dto) throws InterruptedException {
        log.info("Received email request {}", dto);
        Thread.sleep(10_000);

        log.info("Email has been sent: {}", dto);
    }

}
