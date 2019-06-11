package com.vollino.poll.service.poll.rest;

import com.vollino.poll.service.poll.business.PollService;
import com.vollino.poll.service.poll.business.domain.Poll;
import com.vollino.poll.service.poll.business.domain.VoteCount;
import com.vollino.poll.service.vote.business.VoteService;
import com.vollino.poll.service.vote.business.domain.Vote;
import com.vollino.poll.service.vote.business.domain.VoteId;
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

    @MockBean
    private VoteService voteService;

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

    @Test
    public void shouldCreateVote() throws Exception {
        //given
        Long voterId = 1L;
        Long pollId = 2L;
        String option = "Sim";
        Vote vote = new Vote(new VoteId(voterId, pollId), option);

        given(voteService.create(vote)).willReturn(vote);

        //when
        ResultActions response = mockMvc.perform(post("/polls/{pollId}/votes", pollId)
                .content("{" +
                        "\"voterId\": 1," +
                        "\"pollOption\": \"Sim\"" +
                    "}")
                .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{" +
                        "\"id\": {" +
                            "\"voterId\": 1," +
                            "\"pollId\": 2" +
                        "}," +
                        "\"pollOption\": \"Sim\"" +
                    "}"));
        verify(voteService).create(vote);
    }
}