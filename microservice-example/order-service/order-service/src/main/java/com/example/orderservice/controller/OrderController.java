package com.example.orderservice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.service.OrderService;
import com.kma.openapiexample.controller.rest.api.OrderApi;
import com.kma.openapiexample.controller.rest.model.OrderDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public ResponseEntity<Void> createSendOrder(@Valid final OrderDto orderDto) {
        orderService.createOrder(orderDto);
        return ResponseEntity.noContent().build();
    }
}
