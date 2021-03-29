package com.example.service;

import java.awt.print.Book;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final BookService bookService;

    @Scheduled(cron = "${my-prop}")
    void test3() {
        log.info("scheduled task. cron");
        bookService.multipleBookUpdate();
    }

}
