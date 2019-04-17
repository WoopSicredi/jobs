package com.vollino.poll.service.topic;

import com.vollino.poll.service.poll.PollRepository;
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
public class TopicRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private PollRepository pollRepository;

    @Test
    public void shouldCreateTopic() throws Exception {
        //given
        Topic received = new Topic(null, "Topic description");
        Topic persisted = new Topic(1L, "Topic description");
        given(topicRepository.save(received)).willReturn(persisted);

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/topic")
            .content("{\"description\": \"Topic description\"}")
            .contentType(MediaType.APPLICATION_JSON));

        //then
        verify(topicRepository).save(received);
        response.andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(
                    "{\"id\": 1, \"description\": \"Topic description\"}"));
    }

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
}