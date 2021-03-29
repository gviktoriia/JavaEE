package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.after;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(properties = "my-prop=0/2 * * * * *")
class SchedulerTest {

    @MockBean
    private BookService bookService;

    @Test
    void test() {
        reset(bookService);

        verify(bookService, timeout(5000)).multipleBookUpdate();
    }


}