package br.com.sicredi.voting.controller;

import br.com.sicred.voting.GlobalExceptionHandler;
import br.com.sicred.voting.controller.TopicController;
import br.com.sicred.voting.dto.TopicDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.repository.TopicRepository;
import br.com.sicredi.voting.TestUtil;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TopicControllerTest {
    @InjectMocks
    private TopicController topicController;
    private MockMvc mockMvc;
    @Mock
    private TopicRepository topicRepository;
    private Faker faker;
    private Random random;

    @Before
    public void setup() {
        this.faker = new Faker();
        this.random = new Random();
        this.mockMvc = MockMvcBuilders.standaloneSetup(topicController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void givenEmptyDescriptionShouldReturnError() throws Exception {
        //Arrange is not needed here
        //Act
        mockMvc.perform(post("/pauta")
                .content(TestUtil.convertObjectToJsonBytes(new TopicDto()))
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                //Assert
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenValidInputShouldReturnEntity() throws Exception {
        //Arrange
        Topic topic = Topic.builder()
                .description(faker.beer().style())
                .id(random.nextLong()).build();
        when(topicRepository.save(any())).thenReturn(topic);
        //Act
        mockMvc.perform(post("/pauta")
                .content(TestUtil.convertObjectToJsonBytes(
                        TopicDto.builder()
                                .description(faker.artist().name())
                                .build()))
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))))
                .andExpect(jsonPath("$.id", is(topic.getId())));
    }
}
