package com.vollino.poll.service.poll;

import com.vollino.poll.service.poll.rest.PollRestController;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{" +
                "\"id\": 1," +
                "\"topicId\": 2," +
                "\"description\": \"Poll description\"," +
                "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
            "}"));
        verify(pollService).create(received);
    }
}