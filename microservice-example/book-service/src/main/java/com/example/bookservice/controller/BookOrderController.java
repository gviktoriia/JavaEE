package com.example.bookservice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookservice.service.BookService;
import com.kma.openapiexample.controller.rest.api.BookOrderApi;
import com.kma.openapiexample.controller.rest.model.BookOrderDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookOrderController implements BookOrderApi {

    private final BookService bookService;

    @Override
    public ResponseEntity<Void> createBookOrder(@Valid final BookOrderDto bookOrderDto) {
        bookService.createBookOrder(bookOrderDto);
        return ResponseEntity.noContent().build();
    }
}
