package com.vollino.poll.service.topic;

import com.vollino.poll.service.topic.rest.TopicRestController;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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
}