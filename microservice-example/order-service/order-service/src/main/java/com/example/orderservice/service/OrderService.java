package com.example.orderservice.service;

import org.springframework.stereotype.Service;

import com.kma.openapiexample.controller.rest.model.OrderDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

    public void createOrder(final OrderDto orderDto) {
        log.info("Create new delivery order: {}", orderDto);
    }

}
