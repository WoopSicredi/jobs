package com.sicredi.test.web.controller;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.persistence.model.VoteCount;
import com.sicredi.test.persistence.model.VoteOption;
import com.sicredi.test.persistence.service.ITopicPersistenceService;
import com.sicredi.test.persistence.service.IVotePersistenceService;
import com.sicredi.test.web.converter.PollDtoToPollConverter;
import com.sicredi.test.web.converter.PollResultsConverter;
import com.sicredi.test.web.converter.TopicCreationDtoToTopicConverter;
import com.sicredi.test.web.converter.TopicToTopicDtoConverter;
import com.sicredi.test.web.dto.PollCreationDto;
import com.sicredi.test.web.dto.TopicCreationDto;
import com.sicredi.test.web.dto.VoteDto;
import com.sicredi.test.web.exception.ExpiredTopicException;
import com.sicredi.test.web.exception.OpenTopicException;
import com.sicredi.test.web.exception.PollAlreadyCreatedException;
import com.sicredi.test.web.validator.TopicValidator;

@RunWith(MockitoJUnitRunner.class)
public class TopicControllerTest {

    @Mock
    private ITopicPersistenceService topicService;
    @Mock
    private IVotePersistenceService voteService;
    @Mock
    private TopicValidator topicValidator;
    @Mock
    private PollResultsConverter pollResultsConverter;

    @Mock
    private PollDtoToPollConverter pollDtoToPollConverter;
    @Mock
    private TopicCreationDtoToTopicConverter topicCreationDtoToTopicConverter;
    @Mock
    private TopicToTopicDtoConverter topicToTopicDtoConverter;

    @InjectMocks
    private TopicController topicController;

    @Test
    public void shouldCallTopicServiceAndConventWhenCreating() {
        // given
        TopicCreationDto topicCreationDto = mock(TopicCreationDto.class);
        Topic topic = mock(Topic.class);
        given(topicCreationDtoToTopicConverter.convert(topicCreationDto)).willReturn(topic);

        // when
        topicController.create(topicCreationDto);

        // then
        verify(topicCreationDtoToTopicConverter).convert(topicCreationDto);
        verify(topicService).create(topic);
    }

    @Test
    public void shouldCallConverterAndServiceWhenFindAll() {
        // given
        Topic topic1 = mock(Topic.class);
        Topic topic2 = mock(Topic.class);
        Topic topic3 = mock(Topic.class);
        ArrayList<Topic> topics = Lists.newArrayList(topic1, topic2, topic3);
        given(topicService.findAll()).willReturn(topics);

        // when
        topicController.findAll();

        // then
        verify(topicService).findAll();
        verify(topicToTopicDtoConverter, times(3)).convert(any());
    }

    @Test
    public void shouldCallConverterAndServiceWhenFindById() {
        // given
        long topicId = 1234L;
        Topic topic = mock(Topic.class);
        given(topicService.findById(topicId)).willReturn(topic);

        // when
        topicController.findById(topicId);

        // then
        verify(topicService).findById(topicId);
        verify(topicToTopicDtoConverter).convert(topic);
    }

    @Test
    public void shouldCallCreatePollWhenDoNotExist() {
        // given
        long topicId = 1234L;
        PollCreationDto pollDto = mock(PollCreationDto.class);
        Topic topic = mock(Topic.class);
        Poll poll = mock(Poll.class);
        given(topicService.findById(topicId)).willReturn(topic);
        given(pollDtoToPollConverter.convert(pollDto)).willReturn(poll);

        // when
        topicController.openPoll(topicId, pollDto);

        // then
        verify(topicService).createPoll(poll, topic);
        verify(pollDtoToPollConverter).convert(pollDto);
    }

    @Test
    public void shouldThrownPollAlreadyCreatedExceptionWhenPollExist() {
        // given
        long topicId = 1234L;
        PollCreationDto pollDto = mock(PollCreationDto.class);
        Topic topic = mock(Topic.class);
        given(topicService.findById(topicId)).willReturn(topic);
        doThrow(PollAlreadyCreatedException.class).when(topicValidator).validatePollIsNotCreated(topic);

        // when
        catchException(topicController).openPoll(topicId, pollDto);

        // then
        verify(topicService, never()).createPoll(any(), any());
        assertThat(caughtException(), instanceOf(PollAlreadyCreatedException.class));
    }

    @Test
    public void shouldCallValidateAndCreteWhenVoteIsValid() {
        // given
        long topicId = 1234L;
        VoteDto voteDto = mock(VoteDto.class);
        given(voteDto.getUsername()).willReturn("user");
        given(voteDto.getVoteOption()).willReturn(VoteOption.YES);

        // when
        topicController.vote(topicId, voteDto);

        // then
        verify(topicValidator).validateForVote(topicId, "user", VoteOption.YES);
        verify(voteService).createVote(topicId, "user", VoteOption.YES);
    }

    @Test
    public void shouldThrowExceptionWhenPollIsExpired() {
        // given
        long topicId = 1234L;
        VoteDto voteDto = mock(VoteDto.class);
        doThrow(ExpiredTopicException.class).when(topicValidator).validateForVote(anyLong(), anyString(), any());
        given(voteDto.getUsername()).willReturn("user");
        given(voteDto.getVoteOption()).willReturn(VoteOption.YES);

        // when
        catchException(topicController).vote(topicId, voteDto);

        // then
        assertThat(caughtException(), instanceOf(ExpiredTopicException.class));
        verify(voteService, never()).createVote(anyLong(), anyString(), any());
    }

    @Test
    public void shouldGetTopicAndConvertResultsWhenPollIsFinished() {
        // given
        long topicId = 1234L;
        Topic topic = mock(Topic.class);
        List<VoteCount> votes = new ArrayList<>();
        given(voteService.findByTopicId(topicId)).willReturn(votes);
        given(topicService.findById(topicId)).willReturn(topic);

        // when
        topicController.result(topicId);

        // then
        verify(topicValidator).validatePollForGetResults(topicId);
        verify(topicService).findById(topicId);
        verify(pollResultsConverter).convert(votes, topic);
    }

    @Test
    public void shouldThrowExceptionWhenTopicIsOpen() {
        // given
        long topicId = 1234L;
        doThrow(OpenTopicException.class).when(topicValidator).validatePollForGetResults(anyLong());
        given(voteService.findByTopicId(topicId)).willReturn(null);

        // when
        catchException(topicController).result(topicId);

        // then
        assertThat(caughtException(), instanceOf(OpenTopicException.class));
        verify(topicService, never()).findById(anyLong());
        verify(pollResultsConverter, never()).convert(anyList(), any());
    }
}
