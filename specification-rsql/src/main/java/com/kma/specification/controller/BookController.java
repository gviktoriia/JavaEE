package com.kma.specification.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kma.specification.domain.dto.BookDto;
import com.kma.specification.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books/all")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books")
    public List<BookDto> searchBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/newest")
    public List<BookDto> getNewestBooks() {
        return bookService.getNewestBook();
    }

    @GetMapping("/author/{id}/books")
    public List<BookDto> getAuthorBooks(@PathVariable("id") final int authorId) {
        return bookService.getAuthorBooks(authorId);
    }
}
