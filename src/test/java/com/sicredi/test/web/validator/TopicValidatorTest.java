package com.sicredi.test.web.validator;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
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
import com.sicredi.test.persistence.service.IVoteService;
import com.sicredi.test.web.exception.ClosedTopicException;
import com.sicredi.test.web.exception.ExpiredTopicException;
import com.sicredi.test.web.exception.InvalidTopicException;
import com.sicredi.test.web.exception.InvalidVoteOptionException;
import com.sicredi.test.web.exception.UserAlreadyVoteException;

@RunWith(MockitoJUnitRunner.class)
public class TopicValidatorTest {

    @Mock
    private ITopicPersistenceService topicService;
    @Mock
    private IVoteService voteService;
    @InjectMocks
    private TopicValidator validator;

    @Test
    public void shouldThrowExceptioWhenTipicIsInvalid() {
        // given
        long topicId = 1234L;
        String username = "user";
        given(topicService.findById(topicId)).willReturn(null);

        // when
        catchException(validator).validateForVote(topicId, username, VoteOption.YES);
        // then

        assertThat(caughtException(), instanceOf(InvalidTopicException.class));
    }

    @Test
    public void shouldThrowExceptioWhenPollDoNotExist() {
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
    public void shouldThrowExceptioWhenUserAlreadyVote() {
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
    public void shouldThrowExceptioWhenPollIsExpired() {
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
    public void shouldThrowExceptioWhenVoteOptionIsNull() {
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
}
