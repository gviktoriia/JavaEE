package com.example.demo.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.dto.SendEmailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Sender {

    private final JmsTemplate jmsTemplate;
    @Value("${notification.queue}")
    private final String queueName;

    public void sendMessage(final SendEmailDto dto) {
        log.info("Send email request {} to {}", dto, queueName);
        jmsTemplate.convertAndSend(queueName, dto);
    }

}
