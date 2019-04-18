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
import java.time.Duration;
import java.time.ZonedDateTime;

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

    @Test
    public void shouldGetPollWithResults() {
        //given
        Long topicId = createTopicAndReturnId();
        String pollDescription = "Poll description";
        String endDate = ZonedDateTime.now().plus(Duration.ofMinutes(5)).toString();

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

                //    "[{\"option\": \"count\": \"Não\": 3}, {\"option\": \"count\": \"Não\": 2}]"));
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
                ZonedDateTime.now().plus(Duration.ofMinutes(5)).toString());
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