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
import static org.hamcrest.Matchers.*;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
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
            .post("/topics")
        .then()
            .assertThat().statusCode(201)
            .and().body("id", notNullValue())
            .and().body("description", equalTo("Topic description"));
    }

    @Test
    public void shouldNotAcceptTopicWithoutDescription() {
        given().port(port)
            .contentType(ContentType.JSON)
            .body("{\"description\": \"\"}")
        .when()
            .post("/topics")
        .then()
            .assertThat().statusCode(400)
            .and().body("errors", containsInAnyOrder("description is mandatory"));
    }

    @Test
    public void shouldCreatePoll() {
        //given
        Long topicId = createTopicAndReturnId();

        //when-then
        given().port(port)
            .contentType(ContentType.JSON)
            .body(String.format("{" +
                "\"description\": \"Poll description\"," +
                "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
            "}", topicId))
        .when()
            .post("/topics/{topicId}/polls", topicId)
        .then()
            .assertThat().statusCode(201)
            .and().body("id", notNullValue())
            .and().body("topicId", equalTo(topicId.intValue()))
            .and().body("description", equalTo("Poll description"))
            .and().body("endDate", equalTo("2019-04-16T18:19:00-03:00[Brazil/East]"));
    }

    @Test
    public void shouldCastVote() {
        //given
        Long topicId = createTopicAndReturnId();
        Long pollId = createPollAndReturnId(topicId);

        //when-then
        given().port(port)
            .contentType(ContentType.JSON)
            .body(String.format("{" +
                    "\"voterId\": 1," +
                    "\"pollOption\": \"Sim\"" +
                "}", pollId))
        .when()
            .post("/polls/{pollId}/votes", pollId)
        .then()
            .assertThat().statusCode(201)
            .and().body("id.pollId", equalTo(pollId.intValue()))
            .and().body("id.voterId", equalTo(1))
            .and().body("pollOption", equalTo("Sim"));
    }

    private Long createTopicAndReturnId() {
        return given().port(port)
            .contentType(ContentType.JSON)
            .body("{\"description\": \"Topic description\"}")
            .post("/topics")
            .body().jsonPath().getLong("id");
    }

    private Long createPollAndReturnId(Long topicId) {
        return given().port(port)
            .contentType(ContentType.JSON)
            .body(String.format("{" +
                "\"description\": \"Poll description\"," +
                "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
            "}", topicId))
            .post("/topics/{topicId}/polls", topicId)
            .body().jsonPath().getLong("id");
    }
}