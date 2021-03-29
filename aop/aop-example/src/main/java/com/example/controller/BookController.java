package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BookDto;
import com.example.service.BookService;
import com.example.dto.EditBookDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBootById(@PathVariable("id") final int id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> editBoot(
        @PathVariable("id") final int id,
        @RequestBody final EditBookDto editBookDto
    ) {
        bookService.updateBook(id, editBookDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/multiple")
    public ResponseEntity<Void> editMultipleBooks() {
        bookService.multipleBookUpdate();
        return ResponseEntity.noContent().build();
    }

}
