package com.vollino.poll.service.poll.vote;

import com.vollino.poll.service.exception.DataIntegrityException;
import com.vollino.poll.service.poll.Clock;
import com.vollino.poll.service.poll.Poll;
import com.vollino.poll.service.poll.PollRepository;
import com.vollino.poll.service.poll.exception.ClosedPollException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ValidationAutoConfiguration.class, VoteService.class})
public class VoteServiceTest {

    @MockBean
    private VoteRepository voteRepository;

    @MockBean
    private PollRepository pollRepository;

    @MockBean
    private Clock clock;

    @Autowired
    private VoteService voteService;

    @Test
    public void shouldCreateVoteWithOptionSim() {
        //given
        Long pollId = 2L;
        Vote vote = new Vote(new VoteId(1L, pollId), "Sim");
        ZonedDateTime now = ZonedDateTime.now();
        Poll poll = createPoll(pollId, now);

        given(pollRepository.findById(anyLong())).willReturn(Optional.of(poll));
        given(clock.now()).willReturn(now);

        //when
        voteService.create(vote);

        //then
        verify(pollRepository).findById(pollId);
        verify(voteRepository).save(vote);
    }

    @Test
    public void shouldCreateVoteWithOptionNao() {
        //given
        Long pollId = 2L;
        Vote vote = new Vote(new VoteId(1L, pollId), "Não");
        ZonedDateTime now = ZonedDateTime.now();
        Poll poll = createPoll(pollId, now);

        given(pollRepository.findById(anyLong())).willReturn(Optional.of(poll));
        given(clock.now()).willReturn(now);

        //when
        voteService.create(vote);

        //then
        verify(pollRepository).findById(pollId);
        verify(voteRepository).save(vote);
    }

    @Test
    public void shouldRejectVoteWithOptionDifferentThanSimOrNao() {
        //given
        Vote vote = new Vote(new VoteId(1L, 2L), "Any");

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                voteService.create(vote), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("pollOption is mandatory and accepts only values 'Sim' or 'Não'");
    }

    @Test
    public void shouldRejectVoteWithoutAVoterId() {
        //given
        Vote vote = new Vote(new VoteId(null, 2L), "Sim");

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                voteService.create(vote), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("voterId is mandatory");
    }

    @Test
    public void shouldRejectVoteWithoutAPollId() {
        //given
        Vote vote = new Vote(new VoteId(1L, null), "Sim");

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                voteService.create(vote), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("pollId is mandatory");
    }

    @Test
    public void shouldRejectVoteWithoutAPollOption() {
        //given
        Vote vote = new Vote(new VoteId(1L, 2L), "");

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                voteService.create(vote), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("pollOption is mandatory and accepts only values 'Sim' or 'Não'");
    }

    @Test
    public void shouldRejectVoteWhenPollDoesNotExist() {
        //given
        Long pollId = 2L;
        Vote vote = new Vote(new VoteId(1L, pollId), "Sim");

        given(pollRepository.findById(pollId)).willReturn(Optional.empty());

        //when
        DataIntegrityException thrown = catchThrowableOfType(() ->
                voteService.create(vote), DataIntegrityException.class);

        //then
        verify(pollRepository).findById(pollId);
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Poll with id=2 not found");
    }

    @Test
    public void shouldRejectCreationWhenVoterHasAlreadyVotedInThePoll() {
        //given
        Long pollId = 1L;
        VoteId voteId = new VoteId(2L, pollId);
        Vote vote = new Vote(voteId, "Sim");
        ZonedDateTime now = ZonedDateTime.now();
        Poll poll = createPoll(pollId, now);

        given(pollRepository.findById(anyLong())).willReturn(Optional.of(poll));
        given(clock.now()).willReturn(now);
        given(voteRepository.existsById(voteId)).willReturn(true);

        //when
        DataIntegrityException thrown = catchThrowableOfType(() ->
                voteService.create(vote), DataIntegrityException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Voter with id=1 has already voted in Poll 2");
    }

    @Test
    public void shouldRejectCreationWhenPollHasJustClosed() {
        //given
        Long pollId = 1L;
        VoteId voteId = new VoteId(2L, pollId);
        Vote vote = new Vote(voteId, "Sim");
        ZonedDateTime pollEndDate = ZonedDateTime.parse("2019-04-18T10:28-03:00[Brazil/East]");
        ZonedDateTime now = pollEndDate.plus(Duration.ofMillis(1));
        Poll poll = createPoll(pollId, pollEndDate);

        given(clock.now()).willReturn(now);
        given(pollRepository.findById(pollId)).willReturn(Optional.of(poll));
        given(voteRepository.existsById(voteId)).willReturn(true);

        //when
        ClosedPollException thrown = catchThrowableOfType(() ->
                voteService.create(vote), ClosedPollException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage())
                .isEqualTo("Poll with id=1 has closed at 2019-04-18T10:28-03:00[Brazil/East]");
    }

    private Poll createPoll(Long pollId, ZonedDateTime endDate) {
        return new Poll(pollId, null, null, endDate);
    }
}