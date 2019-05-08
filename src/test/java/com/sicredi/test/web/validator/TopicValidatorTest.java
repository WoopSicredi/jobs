package com.sicredi.test.web.validator;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.VoteOption;
import com.sicredi.test.persistence.service.ITopicPersistenceService;
import com.sicredi.test.persistence.service.IVotePersistenceService;
import com.sicredi.test.web.exception.ClosedTopicException;
import com.sicredi.test.web.exception.ExpiredTopicException;
import com.sicredi.test.web.exception.InvalidTopicException;
import com.sicredi.test.web.exception.InvalidVoteOptionException;
import com.sicredi.test.web.exception.OpenTopicException;
import com.sicredi.test.web.exception.PollAlreadyCreatedException;
import com.sicredi.test.web.exception.UserAlreadyVoteException;

@RunWith(MockitoJUnitRunner.class)
public class TopicValidatorTest {

    @Mock
    private ITopicPersistenceService topicService;
    @Mock
    private IVotePersistenceService voteService;
    @InjectMocks
    private TopicValidator validator;

    @Test
    public void shouldThrowExceptionWhenTopicIsInvalid() {
        // given
        String username = "user";
        given(topicService.findById(anyLong())).willReturn(null);

        // when
        catchException(validator).validateForVote(anyLong(), username, VoteOption.YES);
        // then

        assertThat(caughtException(), instanceOf(InvalidTopicException.class));
    }

    @Test
    public void shouldThrowExceptionWhenPollDoNotExist() {
        // given
        long topicId = 1234L;
        String username = "user";
        Topic topic = mock(Topic.class);
        given(topic.getPoll()).willReturn(null);
        given(topicService.findById(topicId)).willReturn(topic);

        // when
        catchException(validator).validateForVote(topicId, username, VoteOption.YES);
        // then

        assertThat(caughtException(), instanceOf(ClosedTopicException.class));
    }

    @Test
    public void shouldThrowExceptionWhenUserAlreadyVote() {
        // given
        long topicId = 1234L;
        String username = "user";
        Topic topic = mock(Topic.class);
        Poll poll = mock(Poll.class);
        given(topic.getPoll()).willReturn(poll);
        Date fiveMinutesBefore = Date.from(Instant.now().minus(5, ChronoUnit.MINUTES));
        given(poll.getCreatedOn()).willReturn(fiveMinutesBefore);
        given(poll.getDurationInMinutes()).willReturn(6);
        given(topicService.findById(topicId)).willReturn(topic);
        given(voteService.userAlreadyVote(username, topicId)).willReturn(true);

        // when
        catchException(validator).validateForVote(topicId, username, VoteOption.YES);
        // then

        assertThat(caughtException(), instanceOf(UserAlreadyVoteException.class));
    }

    @Test
    public void shouldThrowExceptionWhenPollIsExpired() {
        // given
        long topicId = 1234L;
        String username = "user";
        Topic topic = mock(Topic.class);
        Poll poll = mock(Poll.class);
        given(topic.getPoll()).willReturn(poll);
        Date fiveMinutesBefore = Date.from(Instant.now().minus(5, ChronoUnit.MINUTES));
        given(poll.getCreatedOn()).willReturn(fiveMinutesBefore);
        given(poll.getDurationInMinutes()).willReturn(4);
        given(topicService.findById(topicId)).willReturn(topic);

        // when
        catchException(validator).validateForVote(topicId, username, VoteOption.YES);
        // then

        assertThat(caughtException(), instanceOf(ExpiredTopicException.class));
    }

    @Test
    public void shouldThrowExceptionWhenVoteOptionIsNull() {
        // given
        long topicId = 1234L;
        String username = "user";
        Topic topic = mock(Topic.class);
        Poll poll = mock(Poll.class);
        given(topic.getPoll()).willReturn(poll);
        given(topicService.findById(topicId)).willReturn(topic);

        // when
        catchException(validator).validateForVote(topicId, username, null);
        // then

        assertThat(caughtException(), instanceOf(InvalidVoteOptionException.class));
    }

    @Test
    public void shouldThrowExceptionWhenPollIsCreated() {
        // given
        Topic topic = mock(Topic.class);
        Poll poll = mock(Poll.class);
        given(topic.getPoll()).willReturn(poll);

        // when
        catchException(validator).validatePollIsNotCreated(topic);
        // then

        assertThat(caughtException(), instanceOf(PollAlreadyCreatedException.class));
    }

    @Test
    public void shouldThrowExceptionWhenTopicIsNull() {
        // when
        catchException(validator).validatePollIsNotCreated(null);
        // then

        assertThat(caughtException(), instanceOf(InvalidTopicException.class));
    }

    @Test
    public void shouldThrowExceptionWhenTopicIsNullAndValidatePollForGetResults() {
        // given
        given(topicService.findById(anyLong())).willReturn(null);

        // when
        catchException(validator).validatePollForGetResults(anyLong());
        // then

        assertThat(caughtException(), instanceOf(InvalidTopicException.class));
    }

    @Test
    public void shouldThrowExceptionWhenTopicIsClosedAndValidatePollForGetResults() {
        // given
        Topic topic = mock(Topic.class);
        given(topic.getPoll()).willReturn(null);
        given(topicService.findById(anyLong())).willReturn(topic);

        // when
        catchException(validator).validatePollForGetResults(anyLong());
        // then

        assertThat(caughtException(), instanceOf(ClosedTopicException.class));
    }

    @Test
    public void shouldThrowExceptionWhenPollIsOpen() {
        // given
        long topicId = 1234L;
        Topic topic = mock(Topic.class);
        Poll poll = mock(Poll.class);
        given(topic.getPoll()).willReturn(poll);
        Date fiveMinutesBefore = Date.from(Instant.now().minus(5, ChronoUnit.MINUTES));
        given(poll.getCreatedOn()).willReturn(fiveMinutesBefore);
        given(poll.getDurationInMinutes()).willReturn(10);
        given(topicService.findById(topicId)).willReturn(topic);

        // when
        catchException(validator).validatePollForGetResults(topicId);
        // then

        assertThat(caughtException(), instanceOf(OpenTopicException.class));
    }

    @Test
    public void shouldValidateOk() {
        // given
        long topicId = 1234L;
        Topic topic = mock(Topic.class);
        Poll poll = mock(Poll.class);
        given(topic.getPoll()).willReturn(poll);
        Date fiveMinutesBefore = Date.from(Instant.now().minus(5, ChronoUnit.MINUTES));
        given(poll.getCreatedOn()).willReturn(fiveMinutesBefore);
        given(poll.getDurationInMinutes()).willReturn(4);
        given(topicService.findById(topicId)).willReturn(topic);

        // when
        validator.validatePollForGetResults(topicId);

        // then
        assertTrue(true);
    }
}
