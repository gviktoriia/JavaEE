package com.example.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.model.BookDto;
import com.example.demo.model.EditBookDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookService {

    @Cacheable(cacheNames = "books", key = "#id")
    public BookDto getBookById(final int id) {
        log.info("Load book by id: {}", id);
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) { }

        return BookDto.of(id, "book-" + id);
    }

    @CacheEvict(cacheNames = "books", key = "#id")
    public void updateBook(final int id, final EditBookDto editBookDto) {
        log.info("Update book: {}. edit request: {}", id, editBookDto);
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    public void multipleBookUpdate() {
        log.info("Update multiple books");
    }

}
