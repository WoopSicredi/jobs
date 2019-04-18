package com.vollino.poll.service.poll;

import com.vollino.poll.service.poll.rest.PollRestController;
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

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PollRestController.class)
public class PollRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PollService pollService;

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
    public void shouldGetPollWithResults() throws Exception {
        //given
        Long topicId = 1L;
        Long pollId = 2L;
        Poll poll = new Poll(pollId, topicId, "Poll description",
                ZonedDateTime.parse("2019-04-16T18:19:00-03:00[Brazil/East]"));
        poll.setResults(Lists.newArrayList(
                new VoteCount("Não", 1L), new VoteCount("Sim", 2L)));

        given(pollService.getPoll(pollId)).willReturn(Optional.of(poll));

        //when
        ResultActions response = mockMvc.perform(get("/polls/{pollId}", pollId)
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        response.andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(content().json("{" +
                    "\"id\": 2," +
                    "\"topicId\": 1," +
                    "\"description\": \"Poll description\"," +
                    "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"," +
                    "\"results\": [" +
                        "{\"option\": \"Não\", \"count\": 1}," +
                        "{\"option\": \"Sim\", \"count\": 2}" +
                    "]" +
                "}"));
        verify(pollService).getPoll(pollId);
    }

    @Test
    public void shouldReturnNotFoundIfPollDoesNotExist() throws Exception {
        //given
        Long pollId = 2L;

        given(pollService.getPoll(pollId)).willReturn(Optional.empty());

        //when
        ResultActions response = mockMvc.perform(get("/polls/{pollId}", pollId)
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        response.andExpect(status().is(404));
        verify(pollService).getPoll(pollId);
    }
}