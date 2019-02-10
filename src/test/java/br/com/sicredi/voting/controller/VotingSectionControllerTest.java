package br.com.sicredi.voting.controller;

import br.com.sicred.voting.GlobalExceptionHandler;
import br.com.sicred.voting.controller.VotingSectionController;
import br.com.sicred.voting.dto.VotingSectionDto;
import br.com.sicred.voting.service.VotingSectionService;
import br.com.sicredi.voting.TestUtil;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class VotingSectionControllerTest {
    @InjectMocks
    private VotingSectionController votingSectionController;
    private MockMvc mockMvc;
    @Mock
    private VotingSectionService votingSectionService;
    private Faker faker;
    private Random random;


    @Autowired
    private WebApplicationContext appCtx;



    @Before
    public void setup() {
        this.faker = new Faker();
        this.random = new Random();
        this.mockMvc = MockMvcBuilders.standaloneSetup(votingSectionController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void givenInvalidInputShouldReturnErrorCode() throws Exception {
        //Arrange
        when(votingSectionService.createVotingSection(any())).thenThrow(new IllegalArgumentException());
        //Act
        mockMvc.perform(post("/secao")
                .content(TestUtil.convertObjectToJsonBytes(
                        VotingSectionDto
                                .builder()
                                .topicId(random.nextLong())
                                .openingDate(LocalDateTime.now())
                                .build()))
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                //Assert
                .andExpect(status().is4xxClientError());
    }
//
//    @Test
//    public void givenValidInputShouldReturnEntity() throws Exception {
//        //Arrange
//        Topic topic = Topic.builder()
//                .description(faker.beer().style())
//                .id(random.nextLong()).build();
//        when(topicRepository.save(any())).thenReturn(topic);
//        //Act
//        mockMvc.perform(post("/pauta")
//                .content(TestUtil.convertObjectToJsonBytes(
//                        TopicDto.builder()
//                                .description(faker.artist().name())
//                                .build()))
//                .contentType(TestUtil.APPLICATION_JSON_UTF8))
//                //Assert
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", is(not(empty()))))
//                .andExpect(jsonPath("$.id", is(topic.getId())));
//    }
}
