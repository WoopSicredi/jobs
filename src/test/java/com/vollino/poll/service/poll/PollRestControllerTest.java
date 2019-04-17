package com.vollino.poll.service.poll;

import com.vollino.poll.service.topic.TopicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PollRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PollRepository pollRepository;

    @MockBean
    private TopicRepository topicRepository;

    @Test
    public void shouldCreatePoll() throws Exception {
        //given
        Poll received = new Poll(null, 2L, "Poll description",
                ZonedDateTime.parse("2019-04-16T18:19:00-03:00[Brazil/East]"));
        Poll persisted = new Poll(1L, 2L, "Poll description",
                ZonedDateTime.parse("2019-04-16T18:19:00-03:00[Brazil/East]"));
        given(pollRepository.save(received)).willReturn(persisted);

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/poll")
            .content("{" +
                "\"topicId\": 2," +
                "\"description\": \"Poll description\"," +
                "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
            "}")
            .contentType(MediaType.APPLICATION_JSON_UTF8));

        //then
        verify(pollRepository).save(received);
        response.andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json("{" +
                "\"id\": 1," +
                "\"topicId\": 2," +
                "\"description\": \"Poll description\"," +
                "\"endDate\": \"2019-04-16T18:19:00-03:00[Brazil/East]\"" +
            "}"));
    }

    //TODO create corner case tests
    /*
    @Test
    public void shouldNotAcceptTopicWithoutDescription() throws Exception {
        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/topic")
                .content("{\"description\": \"\"}")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        response.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"description\": \"Description is mandatory\"}"));
    }
    */
}