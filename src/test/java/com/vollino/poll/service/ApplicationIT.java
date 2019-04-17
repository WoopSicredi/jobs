package com.vollino.poll.service;

import io.restassured.http.ContentType;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static io.restassured.RestAssured.given;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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

    @Autowired
    private DataSource dataSource;

    @Test
    public void shouldCreateTopic() {
        given().port(port)
            .contentType(ContentType.JSON)
            .body("{\"description\": \"Topic description\"}")
        .when()
            .post("/topic")
        .then()
            .assertThat().statusCode(201)
            .and().body("id", notNullValue())
            .and().body("description", equalTo("Topic description"));
    }

    @Test
    public void shouldCreatePoll() {
        Long topicId = given().port(port)
            .contentType(ContentType.JSON)
            .body("{\"description\": \"Topic description\"}")
            .post("/topic")
                .body().jsonPath().getLong("id");

        given().port(port)
            .contentType(ContentType.JSON)
            .body(String.format("{" +
                "\"topicId\": 1," +
                "\"description\": \"Poll description\"," +
                "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
            "}", topicId))
        .when()
            .post("/poll")
        .then()
            .assertThat().statusCode(201)
            .and().body("id", equalTo(1))
            .and().body("topicId", equalTo(1))
            .and().body("description", equalTo("Poll description"))
            .and().body("endDate", equalTo("2019-04-16T18:19:00-03:00[Brazil/East]"));
    }
}