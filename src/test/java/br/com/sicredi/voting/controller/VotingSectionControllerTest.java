package br.com.sicredi.voting.controller;

import br.com.sicred.voting.GlobalExceptionHandler;
import br.com.sicred.voting.controller.VotingSectionController;
import br.com.sicred.voting.dto.VotingSectionDto;
import br.com.sicred.voting.dto.VotingSectionResultDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.entity.VotingSession;
import br.com.sicred.voting.exception.ClosedSectionVotingException;
import br.com.sicred.voting.exception.ParticipantAlreadyVotedException;
import br.com.sicred.voting.exception.VotingSectionStillOpenException;
import br.com.sicred.voting.service.VotingSectionService;
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

import java.time.LocalDateTime;
import java.util.Random;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        mockMvc.perform(post(String.format("/sessao/%d", random.nextLong()))
                .content(TestUtil.convertObjectToJsonBytes(
                        VotingSectionDto
                                .builder()
                                .openingDate(LocalDateTime.now())
                                .build()))
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                //Assert
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenValidInputShouldReturnEntityWhenCreatingSection() throws Exception {
        //Arrange
        LocalDateTime openingDate = LocalDateTime.now().minusMinutes(10);
        LocalDateTime closingDate = openingDate.plusMinutes(2);
        Topic expectedTopic = Topic.builder()
                .id(random.nextLong())
                .description(faker.ancient().titan())
                .build();
        VotingSession expectedVotingSection = VotingSession
                .builder()
                .openingDate(openingDate)
                .closingDate(closingDate)
                .id(random.nextLong())
                .topic(expectedTopic)
                .build();
        when(votingSectionService.createVotingSection(any())).thenReturn(expectedVotingSection);
        //Act
        mockMvc.perform(post(String.format("/sessao", random.nextLong()))
                .content(TestUtil.convertObjectToJsonBytes(
                        VotingSectionDto
                                .builder()
                                .openingDate(LocalDateTime.now())
                                .build()))
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(not(empty()))))
                .andExpect(jsonPath("$.id", is(expectedVotingSection.getId())));
    }

    @Test
    public void givenInvalidVotingSectionIdShouldReturnError() throws Exception {
        //Arrange
        doThrow(new IllegalArgumentException()).when(votingSectionService)
                .voteForSection(anyLong(), anyLong(), anyBoolean());
        //Act
        String url = String.format("/sessao/%d/%d/%s", random.nextLong(), random.nextLong(), random.nextBoolean());
        mockMvc.perform(put(url))
                //Assert
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenVotingSectionStillOpenShouldReturnError() throws Exception {
        //Arrange
        doThrow(new ClosedSectionVotingException()).when(votingSectionService)
                .voteForSection(anyLong(), anyLong(), anyBoolean());
        //Act
        String url = String.format("/sessao/%d/%d/%s", random.nextLong(), random.nextLong(), random.nextBoolean());
        mockMvc.perform(put(url))
                //Assert
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenParticipantAlreadyVotedShouldReturnError() throws Exception {
        //Arrange
        doThrow(new ParticipantAlreadyVotedException()).when(votingSectionService)
                .voteForSection(anyLong(), anyLong(), anyBoolean());
        //Act
        String url = String.format("/sessao/%d/%d/%s", random.nextLong(), random.nextLong(), random.nextBoolean());
        mockMvc.perform(put(url))
                //Assert
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenValidDataVotedShouldReturnOk() throws Exception {
        //Arrange
        doNothing().when(votingSectionService)
                .voteForSection(anyLong(), anyLong(), anyBoolean());
        //Act
        String url = String.format("/sessao/%d/%d/%s", random.nextLong(), random.nextLong(), random.nextBoolean());
        mockMvc.perform(put(url))
                //Assert
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidSectionIdShouldReturnError() throws Exception {
        //Arrange
        doThrow(new IllegalArgumentException()).when(votingSectionService)
                .voteForSection(anyLong(), anyLong(), anyBoolean());
        //Act
        String url = String.format("/sessao/%d/results", random.nextLong());
        mockMvc.perform(put(url))
                //Assert
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenVotingSectionStillOpenShouldReturnErrorOnGettingResults() throws Exception {
        //Arrange
        doThrow(new VotingSectionStillOpenException()).when(votingSectionService)
                .voteForSection(anyLong(), anyLong(), anyBoolean());
        //Act
        String url = String.format("/sessao/%d/results", random.nextLong());
        mockMvc.perform(put(url))
                //Assert
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenEmptyVotesShouldReturnOkWhenGettingResults() throws Exception {
        //Arrange
        doReturn(VotingSectionResultDto
                .builder()
                .noPercentage(0d)
                .yesPercentage(0d)
                .build())
                .when(votingSectionService)
                .getVotingSectionResult(anyLong());
        //Act
        String url = String.format("/sessao/%d/results", random.nextLong());
        mockMvc.perform(get(url))
                //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.yesPercentage", equalTo(0d)))
                .andExpect(jsonPath("$.noPercentage", equalTo(0d)));
    }

    @Test
    public void givenValidVotesShouldReturnOkWhenGettingResults() throws Exception {
        //Arrange
        VotingSectionResultDto expectedResult = VotingSectionResultDto
                .builder()
                .noPercentage(50d)
                .yesPercentage(50d)
                .build();
        doReturn(expectedResult)
                .when(votingSectionService)
                .getVotingSectionResult(anyLong());
        //Act
        String url = String.format("/sessao/%d/results", random.nextLong());
        mockMvc.perform(get(url))
                //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.yesPercentage", equalTo(expectedResult.getYesPercentage())))
                .andExpect(jsonPath("$.noPercentage", equalTo(expectedResult.getNoPercentage())));
    }


}
