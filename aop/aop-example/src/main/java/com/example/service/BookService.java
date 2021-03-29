package com.example.service;

import org.springframework.stereotype.Service;

import com.example.aop.MyAnnotation;
import com.example.dto.BookDto;
import com.example.dto.EditBookDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

    @MyAnnotation(param1 = 1)
    public BookDto getBookById(final int id) {
        log.info("get by id");
        return BookDto.of(id, "book-" + id);
    }

    @MyAnnotation(param1 = 2)
    public void updateBook(final int id, final EditBookDto editBookDto) {
        log.info("Update book: {}. edit request: {}", id, editBookDto);
    }

    public void multipleBookUpdate() {
        log.info("Update multiple books");
    }

}
