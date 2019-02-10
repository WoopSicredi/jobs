package br.com.sicredi.voting.service;


import br.com.sicred.voting.dto.VotingSectionDto;
import br.com.sicred.voting.dto.VotingSectionResultDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.entity.Vote;
import br.com.sicred.voting.entity.VotingSection;
import br.com.sicred.voting.exception.ClosedSectionVotingException;
import br.com.sicred.voting.exception.ParticipantAlreadyVotedException;
import br.com.sicred.voting.exception.VotingSectionStillOpenException;
import br.com.sicred.voting.repository.TopicRepository;
import br.com.sicred.voting.repository.VotingSectionRepository;
import br.com.sicred.voting.repository.VoteRepository;
import br.com.sicred.voting.service.VotingSectionService;
import br.com.sicred.voting.service.VotingSectionServiceImpl;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class VotingSectionServiceTest {

    private VotingSectionService votingSectionService;
    @Mock
    private VotingSectionRepository votingSectionRepository;
    @Mock
    private TopicRepository topicRepository;
    @Mock
    private VoteRepository voteRepository;
    private Faker faker;
    private Random random;

    @Before
    public void setup() {
        this.votingSectionService = new VotingSectionServiceImpl(
                topicRepository, votingSectionRepository, voteRepository);
        this.faker = new Faker();
        this.random = new Random();
    }


    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidIdPautaThenShouldThrowExceptionWhenCreatingSection() {
        //Arrange
        when(topicRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.votingSectionService.createVotingSection(
                VotingSectionDto.builder()
                        .openingDate(LocalDateTime.now())
                        .topicId(new Random().nextLong())
                        .build());
        //Assert não é necessário aqui pois esperamos que uma exception seja lançada
    }

    @Test
    public void givenValidDataThenShouldReturnEntityWhenCreatingSection() {
        //Arrange
        LocalDateTime dataAbertura = LocalDateTime.now();
        LocalDateTime expectedDataEncerramento = dataAbertura.plusMinutes(1);
        Topic expectedTopic = Topic.builder().description(faker.esports().event()).build();
        VotingSection expectedSecao = VotingSection.builder()
                .id(random.nextLong())
                .openingDate(dataAbertura)
                .closingDate(expectedDataEncerramento)
                .topic(expectedTopic)
                .build();
        when(topicRepository.findById(anyLong())).thenReturn(
                Optional.of(expectedTopic));
        when(votingSectionRepository.save(any())).thenReturn(expectedSecao);
        //Act
        VotingSection votingSection = this.votingSectionService.createVotingSection(
                VotingSectionDto.builder()
                        .topicId(new Random().nextLong())
                        .openingDate(dataAbertura)
                        .build());
        assertThat(expectedSecao, equalTo(votingSection));
    }


    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidSectionIdThenShouldThrowExceptionWhenVoting() {
        //Arrange
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.votingSectionService.createVotingSection(
                VotingSectionDto.builder()
                        .topicId(new Random().nextLong())
                        .openingDate(LocalDateTime.now())
                        .build());
        //Assert is not needed here cause we expect an exception to be thrown
    }


    @Test(expected = ClosedSectionVotingException.class)
    public void givenValidSectionIdAlreadyFinishedThenShouldThrowExceptionWhenVoting() {
        //Arrange
        VotingSection secao = VotingSection.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().minusDays(9))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.of(secao));
        //Act
        this.votingSectionService.voteForSection(secao.getId(), random.nextLong(), random.nextBoolean());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test(expected = ParticipantAlreadyVotedException.class)
    public void givenParticipantThatAlreadyVotedShouldThrowExceptionWhenVoting() {
        //Arrange
        Long participantId = random.nextLong();
        VotingSection section = VotingSection.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().plusDays(1))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        Vote vote = Vote.builder()
                .value(random.nextBoolean())
                .date(LocalDateTime.now().minusHours(2))
                .id(random.nextLong())
                .participantId(participantId)
                .build();
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.of(section));
        when(voteRepository.findByVotingSectionAndParticipantIds(any(), any())).thenReturn(Optional.of(vote));
        //Act
        this.votingSectionService.voteForSection(section.getId(), random.nextLong(), random.nextBoolean());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test
    public void givenValidDataVotedShouldBeSuccessfulWhenVoting() {
        //Arrange
        VotingSection section = VotingSection.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().plusDays(1))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.of(section));
        when(voteRepository.findByVotingSectionAndParticipantIds(any(), any())).thenReturn(Optional.empty());
        //Act
        this.votingSectionService.voteForSection(section.getId(), random.nextLong(), random.nextBoolean());
        //Assert
        verify(voteRepository, times(1)).save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidVotingSectionIdThrowException() {
        //Arrange
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.votingSectionService.getVotingSectionResult(random.nextLong());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test(expected = VotingSectionStillOpenException.class)
    public void givenVotingSectionStillOpenShouldThrowException() {
        //Arrange
        VotingSection section = VotingSection.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().plusDays(1))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.of(section));
        //Act
        this.votingSectionService.getVotingSectionResult(section.getId());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test
    public void givenEmptyVotingSectionShouldReturnZeroResults() {
        //Arrange
        VotingSection section = VotingSection.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().minusDays(9))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.of(section));
        when(voteRepository.findByVotingSectionId(anyLong())).thenReturn(new ArrayList<>());
        //Act
        VotingSectionResultDto resultDto = this.votingSectionService.getVotingSectionResult(section.getId());
        //Assert
        assertThat(resultDto.getVotingSection(), equalTo(section));
        assertThat(resultDto.getNoPercentage(), is(0d));
        assertThat(resultDto.getYesPercentage(), is(0d));
    }

    @Test
    public void givenVotingSectionWithTenVotesShouldReturnExpectedResults() {
        //Arrange
        VotingSection section = VotingSection.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().minusDays(9))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        List<Vote> votes = IntStream.range(0, 10).mapToObj(value ->
                Vote.builder()
                        .date(LocalDateTime.now())
                        .participantId(random.nextLong())
                        .value(value%2==0)
                        .votingSection(section)
                        .build()).collect(Collectors.toList());
        when(votingSectionRepository.findById(anyLong())).thenReturn(Optional.of(section));
        when(voteRepository.findByVotingSectionId(anyLong())).thenReturn(votes);
        //Act
        VotingSectionResultDto resultDto = this.votingSectionService.getVotingSectionResult(section.getId());
        //Assert
        assertThat(resultDto.getVotingSection(), equalTo(section));
        assertThat(resultDto.getNoPercentage(), is(50d));
        assertThat(resultDto.getYesPercentage(), is(50d));
    }
}
