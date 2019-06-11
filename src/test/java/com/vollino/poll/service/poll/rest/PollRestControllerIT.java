package com.vollino.poll.service.poll.rest;

import io.restassured.http.ContentType;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.DOCKER;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@FlywayTest
@AutoConfigureEmbeddedDatabase(provider = DOCKER)
public class PollRestControllerIT {

    @LocalServerPort
    private int port;

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

    @Test
    public void shouldGetPollWithResults() {
        //given
        Long topicId = createTopicAndReturnId();
        String pollDescription = "Poll description";
        String endDate = ZonedDateTime.now().plus(Duration.ofMinutes(5))
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME);

        Long pollId = createPollAndReturnId(topicId, pollDescription, endDate);
        castVote(pollId, 1L, "Sim");
        castVote(pollId, 2L, "Sim");
        castVote(pollId, 3L, "Não");
        castVote(pollId, 4L, "Não");
        castVote(pollId, 5L, "Sim");

        //when-then
        given().port(port)
            .contentType(ContentType.JSON)
        .when()
            .get("/polls/{pollId}", pollId)
        .then()
            .assertThat().statusCode(200)
            .and().body("id", equalTo(pollId.intValue()))
            .and().body("description", equalTo(pollDescription))
            .and().body("endDate", equalTo(endDate))
            .and().body("results[0].option", equalTo("Não"))
            .and().body("results[0].count", equalTo(2))
            .and().body("results[1].option", equalTo("Sim"))
            .and().body("results[1].count", equalTo(3));
    }

    @Test
    public void shouldGetPollsWithResultsByTopic() {
        //given
        Long topicId = createTopicAndReturnId();
        String pollDescription = "Poll description";
        String endDate = ZonedDateTime.now().plus(Duration.ofMinutes(5))
                .format(DateTimeFormatter.ISO_ZONED_DATE_TIME);

        Long pollId1 = createPollAndReturnId(topicId, pollDescription, endDate);
        castVote(pollId1, 1L, "Sim");
        castVote(pollId1, 2L, "Não");

        Long pollId2 = createPollAndReturnId(topicId, pollDescription, endDate);
        castVote(pollId2, 1L, "Sim");

        //when-then
        given().port(port)
            .contentType(ContentType.JSON)
        .when()
            .get("/topics/{topicId}/polls", topicId)
        .then()
            .assertThat().statusCode(200)
            .and().body("[0].id", equalTo(pollId1.intValue()))
            .and().body("[0].description", equalTo(pollDescription))
            .and().body("[0].endDate", equalTo(endDate))
            .and().body("[0].results[0].option", equalTo("Não"))
            .and().body("[0].results[0].count", equalTo(1))
            .and().body("[0].results[1].option", equalTo("Sim"))
            .and().body("[0].results[1].count", equalTo(1))
            .and().body("[1].id", equalTo(pollId2.intValue()))
            .and().body("[1].description", equalTo(pollDescription))
            .and().body("[1].endDate", equalTo(endDate))
            .and().body("[1].results[0].option", equalTo("Sim"))
            .and().body("[1].results[0].count", equalTo(1));
    }

    private Long createTopicAndReturnId() {
        return given().port(port)
            .contentType(ContentType.JSON)
            .body("{\"description\": \"Topic description\"}")
            .post("/topics")
            .body().jsonPath().getLong("id");
    }

    private Long createPollAndReturnId(Long topicId) {
        return createPollAndReturnId(topicId, "Poll description",
                ZonedDateTime.now().plus(Duration.ofMinutes(10)).format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }

    private Long createPollAndReturnId(Long topicId, String description, String endDate) {
        return given().port(port)
            .contentType(ContentType.JSON)
            .body(String.format("{" +
                "\"description\": \"%s\"," +
                "\"endDate\": \"%s\"" +
            "}", description, endDate))
            .post("/topics/{topicId}/polls", topicId)
            .body().jsonPath().getLong("id");
    }

    private void castVote(Long pollId, Long voterId, String pollOption) {
        given().port(port)
            .contentType(ContentType.JSON)
            .body(String.format("{" +
                "\"voterId\": %d," +
                "\"pollOption\": \"%s\"" +
            "}", voterId, pollOption))
            .when()
            .post("/polls/{pollId}/votes", pollId);
    }
}