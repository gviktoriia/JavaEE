package kma.topic6.springdatasample;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    @Sql("/UserServiceTest/init.sql")
    @Sql(value = "/clean_up.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldSelectUserByEmail() {
        service.createUser("firstName", "lastName", "email1@example.com", "num1");
    }

    @Test
    @Sql("/UserServiceTest/init.sql")
    @Sql(value = "/clean_up.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldSelectUserByEmail_1() {
        assertThat(service.findByEmail("email1@example.com"))
            .isNotNull();
    }

}