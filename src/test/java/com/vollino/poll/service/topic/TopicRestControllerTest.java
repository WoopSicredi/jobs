package com.vollino.poll.service.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TopicRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicRepository topicRepository;

    @Test
    public void shouldCreateTopic() throws Exception {
        //given
        Topic persisted = new Topic(1L, "Topic description");
        given(topicRepository.save(any(Topic.class))).willReturn(persisted);

        //when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/topic")
                .content("{\"description\": \"Topic description\"}")
                .contentType(MediaType.APPLICATION_JSON));

        //then
        response.andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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