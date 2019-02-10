package br.com.sicredi.voting.service;


import br.com.sicred.voting.dto.VotingSessionDto;
import br.com.sicred.voting.dto.VotingSessionResultDto;
import br.com.sicred.voting.entity.Topic;
import br.com.sicred.voting.entity.Vote;
import br.com.sicred.voting.entity.VotingSession;
import br.com.sicred.voting.exception.ClosedSessionVotingException;
import br.com.sicred.voting.exception.ParticipantAlreadyVotedException;
import br.com.sicred.voting.exception.VotingSessionStillOpenException;
import br.com.sicred.voting.repository.TopicRepository;
import br.com.sicred.voting.repository.VotingSessionRepository;
import br.com.sicred.voting.repository.VoteRepository;
import br.com.sicred.voting.service.VotingSessionService;
import br.com.sicred.voting.service.VotingSessionServiceImpl;
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
public class VotingSessionServiceTest {

    private VotingSessionService votingSessionService;
    @Mock
    private VotingSessionRepository votingSessionRepository;
    @Mock
    private TopicRepository topicRepository;
    @Mock
    private VoteRepository voteRepository;
    private Faker faker;
    private Random random;

    @Before
    public void setup() {
        this.votingSessionService = new VotingSessionServiceImpl(
                topicRepository, votingSessionRepository, voteRepository);
        this.faker = new Faker();
        this.random = new Random();
    }


    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidIdPautaThenShouldThrowExceptionWhenCreatingSession() {
        //Arrange
        when(topicRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.votingSessionService.createVotingSession(
                VotingSessionDto.builder()
                        .openingDate(LocalDateTime.now())
                        .topicId(new Random().nextLong())
                        .build());
        //Assert não é necessário aqui pois esperamos que uma exception seja lançada
    }

    @Test
    public void givenValidDataThenShouldReturnEntityWhenCreatingSession() {
        //Arrange
        LocalDateTime openingDate = LocalDateTime.now();
        LocalDateTime closingDate = openingDate.plusMinutes(1);
        Topic expectedTopic = Topic.builder().description(faker.esports().event()).build();
        VotingSession expectedSession = VotingSession.builder()
                .id(random.nextLong())
                .openingDate(openingDate)
                .closingDate(closingDate)
                .topic(expectedTopic)
                .build();
        when(topicRepository.findById(anyLong())).thenReturn(
                Optional.of(expectedTopic));
        when(votingSessionRepository.save(any())).thenReturn(expectedSession);
        //Act
        VotingSession votingSession = this.votingSessionService.createVotingSession(
                VotingSessionDto.builder()
                        .topicId(new Random().nextLong())
                        .openingDate(openingDate)
                        .build());
        assertThat(expectedSession, equalTo(votingSession));
    }


    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidSessionIdThenShouldThrowExceptionWhenVoting() {
        //Arrange
        Topic expectedTopic = Topic.builder().description(faker.esports().event()).build();
        when(topicRepository.findById(anyLong())).thenReturn(
                Optional.of(expectedTopic));
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.votingSessionService.createVotingSession(
                VotingSessionDto.builder()
                        .topicId(new Random().nextLong())
                        .openingDate(LocalDateTime.now())
                        .closingDate(LocalDateTime.now().minusHours(2))
                        .build());
        //Assert is not needed here cause we expect an exception to be thrown
    }


    @Test(expected = IllegalArgumentException.class)
    public void givenClosingDataBeforeOpeningThenShouldThrowExceptionWhenVoting() {
        //Arrange
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.votingSessionService.createVotingSession(
                VotingSessionDto.builder()
                        .topicId(new Random().nextLong())
                        .openingDate(LocalDateTime.now())
                        .build());
        //Assert is not needed here cause we expect an exception to be thrown
    }


    @Test(expected = ClosedSessionVotingException.class)
    public void givenValidSessionIdAlreadyFinishedThenShouldThrowExceptionWhenVoting() {
        //Arrange
        VotingSession session = VotingSession.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().minusDays(9))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.of(session));
        //Act
        this.votingSessionService.voteForSession(session.getId(), random.nextLong(), random.nextBoolean());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test(expected = ParticipantAlreadyVotedException.class)
    public void givenParticipantThatAlreadyVotedShouldThrowExceptionWhenVoting() {
        //Arrange
        Long participantId = random.nextLong();
        VotingSession session = VotingSession.builder()
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
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.of(session));
        when(voteRepository.findByVotingSessionAndParticipantIds(any(), any())).thenReturn(Optional.of(vote));
        //Act
        this.votingSessionService.voteForSession(session.getId(), random.nextLong(), random.nextBoolean());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test
    public void givenValidDataVotedShouldBeSuccessfulWhenVoting() {
        //Arrange
        VotingSession session = VotingSession.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().plusDays(1))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.of(session));
        when(voteRepository.findByVotingSessionAndParticipantIds(any(), any())).thenReturn(Optional.empty());
        //Act
        this.votingSessionService.voteForSession(session.getId(), random.nextLong(), random.nextBoolean());
        //Assert
        verify(voteRepository, times(1)).save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenInvalidVotingSessionIdThrowException() {
        //Arrange
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.empty());
        //Act
        this.votingSessionService.getVotingSessionResult(random.nextLong());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test(expected = VotingSessionStillOpenException.class)
    public void givenVotingSessionStillOpenShouldThrowException() {
        //Arrange
        VotingSession session = VotingSession.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().plusDays(1))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.of(session));
        //Act
        this.votingSessionService.getVotingSessionResult(session.getId());
        //Assert is not needed here cause we expect an exception to be thrown
    }

    @Test
    public void givenEmptyVotingSessionShouldReturnZeroResults() {
        //Arrange
        VotingSession session = VotingSession.builder()
                .openingDate(LocalDateTime.now().minusDays(10))
                .closingDate(LocalDateTime.now().minusDays(9))
                .topic(Topic.builder()
                        .description(faker.artist().name())
                        .id(random.nextLong())
                        .build())
                .id(random.nextLong())
                .build();
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.of(session));
        when(voteRepository.findByVotingSessionId(anyLong())).thenReturn(new ArrayList<>());
        //Act
        VotingSessionResultDto resultDto = this.votingSessionService.getVotingSessionResult(session.getId());
        //Assert
        assertThat(resultDto.getVotingSession(), equalTo(session));
        assertThat(resultDto.getNoPercentage(), is(0d));
        assertThat(resultDto.getYesPercentage(), is(0d));
    }

    @Test
    public void givenVotingSessionWithTenVotesShouldReturnExpectedResults() {
        //Arrange
        VotingSession session = VotingSession.builder()
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
                        .votingSession(session)
                        .build()).collect(Collectors.toList());
        when(votingSessionRepository.findById(anyLong())).thenReturn(Optional.of(session));
        when(voteRepository.findByVotingSessionId(anyLong())).thenReturn(votes);
        //Act
        VotingSessionResultDto resultDto = this.votingSessionService.getVotingSessionResult(session.getId());
        //Assert
        assertThat(resultDto.getVotingSession(), equalTo(session));
        assertThat(resultDto.getNoPercentage(), is(50d));
        assertThat(resultDto.getYesPercentage(), is(50d));
    }
}
