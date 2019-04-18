package com.vollino.poll.service.vote;

import com.vollino.poll.service.exception.DataIntegrityException;
import com.vollino.poll.service.poll.PollRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

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

    @Autowired
    private VoteService voteService;

    @Test
    public void shouldCreateVoteWithOptionSim() {
        //given
        Long pollId = 2L;
        Vote vote = new Vote(new VoteId(1L, pollId), "Sim");

        given(pollRepository.existsById(anyLong())).willReturn(true);

        //when
        voteService.create(vote);

        //then
        verify(pollRepository).existsById(pollId);
        verify(voteRepository).save(vote);
    }

    @Test
    public void shouldCreateVoteWithOptionNao() {
        //given
        Long pollId = 2L;
        Vote vote = new Vote(new VoteId(1L, pollId), "Não");

        given(pollRepository.existsById(anyLong())).willReturn(true);

        //when
        voteService.create(vote);

        //then
        verify(pollRepository).existsById(pollId);
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
                .isEqualTo("pollOption accepts only values 'Sim' or 'Não'");
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
                .isEqualTo("pollOption is mandatory");
    }

    @Test
    public void shouldRejectVoteWhenPollDoesNotExist() {
        //given
        Long pollId = 2L;
        Vote vote = new Vote(new VoteId(1L, pollId), "Sim");

        given(pollRepository.existsById(pollId)).willReturn(false);

        //when
        DataIntegrityException thrown = catchThrowableOfType(() ->
                voteService.create(vote), DataIntegrityException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Poll with id=2 not found");
    }

    @Test
    public void shouldRejectCreationWhenVoterHasAlreadyVotedInThePoll() {
        //given
        Long pollId = 1L;
        VoteId voteId = new VoteId(2L, pollId);
        Vote vote = new Vote(voteId, "Sim");

        given(pollRepository.existsById(pollId)).willReturn(true);
        given(voteRepository.existsById(voteId)).willReturn(true);

        //when
        DataIntegrityException thrown = catchThrowableOfType(() ->
                voteService.create(vote), DataIntegrityException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Voter with id=1 has already voted in Poll 2");
    }
}