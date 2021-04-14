package com.example.notificaiton.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SendEmailReceiver {

    @JmsListener(destination = "${email-notification.queue}")
    void receiveEmail(String message) {
        log.info("Received notification message {}", message);
    }

}
