package kma.topic8.springrestsample;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import kma.topic8.springrestsample.dto.LoginResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyRestControllerIt {

    @LocalServerPort
    void savePort(final int port) {
        // save port of locally starter server during test
        RestAssured.port = port;
    }

    @Test
    void shouldSendRequest() {
        given()
            .body(MyRestControllerTest.class.getResourceAsStream("/request.json"))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("requiredField", "value")
        .when()
            .post("/login")
        .then()
            .header(HttpHeaders.AUTHORIZATION, CoreMatchers.startsWith("generated-jwt"))
            .body("login", CoreMatchers.is("username"))
            .body("message", CoreMatchers.is("success"));
    }

    @Test
    void shouldSendRequest_testResponseAsObject() {
        final LoginResponseDto responseDto = given()
            .body(MyRestControllerTest.class.getResourceAsStream("/request.json"))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("requiredField", "value")
        .when()
            .post("/login")
        .then()
            .header(HttpHeaders.AUTHORIZATION, CoreMatchers.startsWith("generated-jwt"))
            .extract()
            .as(LoginResponseDto.class);

        assertThat(responseDto).isEqualTo(LoginResponseDto.of("username", "success"));
        // alternative way to test only few fields
        assertThat(responseDto)
            .returns("username", LoginResponseDto::getLogin)
            .returns("success", LoginResponseDto::getMessage);
    }

}
