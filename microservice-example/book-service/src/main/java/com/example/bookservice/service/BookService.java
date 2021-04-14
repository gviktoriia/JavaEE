package com.example.bookservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.kma.openapiexample.client.api.OrderApi;
import com.kma.openapiexample.client.model.OrderDto;
import com.kma.openapiexample.controller.rest.model.BookOrderDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final OrderApi orderApi;
    private final JmsTemplate jmsTemplate;
    @Value("${email-notification.queue}")
    private final String emailQueueName;

    public void createBookOrder(final BookOrderDto bookOrderDto) {
        log.info("Create new order for book {} email {}", bookOrderDto.getIsbn(), bookOrderDto.getUserEmail());

        log.info("Creating order");
        orderApi.createSendOrder(
            new OrderDto()
                .email(bookOrderDto.getUserEmail())
                .deliveryAddress(bookOrderDto.getDeliveryAddress())
        );

        log.info("Sending notification");
        jmsTemplate.convertAndSend(emailQueueName, "Order successfully created");
    }

}
