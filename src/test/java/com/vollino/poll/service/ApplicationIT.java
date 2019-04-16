package com.vollino.poll.service;

import io.restassured.http.ContentType;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FlywayTest
@AutoConfigureEmbeddedDatabase(provider = DOCKER)
public class ApplicationIT {

    @LocalServerPort
    private int port;

    @Test
    public void shouldCreateTopic() {
        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"description\": \"Topic description\"}")
        .when()
            .post("/topic")
        .then()
            .assertThat().statusCode(201)
            .and().body("id", equalTo(1))
            .and().body("description", equalTo("Topic description"));
    }
}