package com.vollino.poll.service.topic.rest;

import com.vollino.poll.service.poll.business.PollService;
import com.vollino.poll.service.poll.business.domain.Poll;
import com.vollino.poll.service.poll.business.domain.VoteCount;
import com.vollino.poll.service.topic.business.TopicService;
import com.vollino.poll.service.topic.business.domain.Topic;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TopicRestController.class)
public class TopicRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    @MockBean
    private PollService pollService;

    @Test
    public void shouldCreateTopic() throws Exception {
        //given
        Topic received = new Topic(null, "Topic description");
        Topic created = new Topic(1L, "Topic description");
        given(topicService.create(received)).willReturn(created);

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/topics")
            .content("{\"description\": \"Topic description\"}")
            .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        verify(topicService).create(received);
        response.andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json(
                    "{\"id\": 1, \"description\": \"Topic description\"}"));
    }

    @Test
    public void shouldCreatePoll() throws Exception {
        //given
        Long topicId = 2L;
        Poll received = new Poll(null, topicId, "Poll description",
                ZonedDateTime.parse("2019-04-16T18:19:00-03:00[Brazil/East]"));
        Poll persisted = new Poll(1L, topicId, "Poll description",
                ZonedDateTime.parse("2019-04-16T18:19:00-03:00[Brazil/East]"));
        given(pollService.create(received)).willReturn(persisted);

        //when
        ResultActions response = mockMvc.perform(post("/topics/{topicId}/polls", topicId)
                .content("{" +
                        "\"description\": \"Poll description\"," +
                        "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
                    "}")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{" +
                        "\"id\": 1," +
                        "\"topicId\": 2," +
                        "\"description\": \"Poll description\"," +
                        "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
                    "}"));
        verify(pollService).create(received);
    }

    @Test
    public void shouldGetAllTopics() throws Exception {
        //given
        Topic topic1 = new Topic(1L, "Topic 1 description");
        Topic topic2 = new Topic(2L, "Topic 2 description");

        given(topicService.getAll()).willReturn(Lists.newArrayList(topic1, topic2));

        //when
        ResultActions response = mockMvc
                .perform(MockMvcRequestBuilders.get("/topics")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        verify(topicService).getAll();
        response.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json("[" +
                    "{\"id\": 1, \"description\": \"Topic 1 description\"}," +
                    "{\"id\": 2, \"description\": \"Topic 2 description\"}" +
                "]"));
    }

    @Test
    public void shouldGetPollsByTopic() throws Exception {
        //given
        List<Poll> polls = Lists.newArrayList(
            new Poll(1L, 1L, "descr 1", ZonedDateTime.parse("2019-04-16T18:19:00-03:00[Brazil/East]")),
            new Poll(2L, 1L, "descr 2", ZonedDateTime.parse("2019-05-16T18:19:00-03:00[Brazil/East]")));
        polls.get(1).setResults(Lists.newArrayList(new VoteCount("Sim", 1L), new VoteCount("Não", 2L)));
        Long topicId = 1L;
        given(pollService.getPollsByTopic(any())).willReturn(polls);

        //when
        ResultActions response = mockMvc
                .perform(MockMvcRequestBuilders.get("/topics/{topicId}/polls", topicId)
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        verify(pollService).getPollsByTopic(topicId);
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[" +
                    "{" +
                        "\"id\": 1, " +
                        "\"topicId\": 1, " +
                        "\"description\": \"descr 1\", " +
                        "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
                    "}," +
                    "{" +
                        "\"id\": 2, " +
                        "\"topicId\": 1, " +
                        "\"description\": \"descr 2\", " +
                        "\"endDate\": \"2019-05-16T18:19:00-03:00[Brazil/East]\", " +
                        "\"results\": [" +
                            "{\"option\": \"Sim\", \"count\": 1}," +
                            "{\"option\": \"Não\", \"count\": 2}" +
                        "]" +
                    "}" +
                "]"));
    }
}