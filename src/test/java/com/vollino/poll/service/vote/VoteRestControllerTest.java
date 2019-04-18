package com.vollino.poll.service.vote;

import com.vollino.poll.service.vote.rest.VoteRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@WebMvcTest(VoteRestController.class)
public class VoteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

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