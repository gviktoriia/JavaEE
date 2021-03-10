package kma.topic6.springdatasample;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@MyAnnotation
@DataJpaTest
@DatabaseSetup("/UserRepositoryTest/init.xml")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindAll() {
        assertThat(userRepository.findAll())
            .hasSize(3)
            .extracting(UserEntity::getId, UserEntity::getEmail)
            .containsExactly(
                tuple(1, "email1@example.com"),
                tuple(2, "email2@example.com"),
                tuple(3, "email3@example.com")
            );
    }

    @Test
    void shouldFindAllWithPaging() {
        final Page<UserEntity> users = userRepository.findAllByFirstNameEqualsAndLastNameEquals("asd", "sdf", PageRequest.of(0, 2, Sort.by(Sort.Order.desc("email"))));

        assertThat(users.getContent())
            .hasSize(2)
            .extracting(UserEntity::getId, UserEntity::getEmail)
            .containsExactly(
                tuple(6, "email6@example.com"),
                tuple(3, "email3@example.com")
            );
        assertThat(users.getTotalElements()).isEqualTo(3L);
        assertThat(users.getTotalPages()).isEqualTo(2);
    }


    @ParameterizedTest
//    @MethodSource("myMethod")
    @CsvSource({
        "email1@example.com, true"
    })
    void shouldCheckIfEmailExists(final String email, final boolean expectedResult) {
        assertThat(userRepository.existsByEmail(email)).isEqualTo(expectedResult);
    }

    @Test
//    @Commit
    @ExpectedDatabase(value = "/UserRepositoryTest/init.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    void shouldSave() {
        final UserEntity user = userRepository.save(
            UserEntity.builder()
                .email("Aewgaew")
                .firstName("Agewge")
                .lastName("agegge")
                .build()
        );
    }

    private static Stream<Arguments> myMethod() {
        return Stream.of(
            Arguments.of("email1@example.com", true),
            Arguments.of("unknown-email@example.com", false)
        );
    }

}