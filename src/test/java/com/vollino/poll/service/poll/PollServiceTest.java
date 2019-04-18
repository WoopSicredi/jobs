package com.vollino.poll.service.poll;

import com.vollino.poll.service.exception.DataIntegrityException;
import com.vollino.poll.service.topic.TopicRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ValidationAutoConfiguration.class, PollService.class})
public class PollServiceTest {

    @MockBean
    private PollRepository pollRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private Clock clock;

    @Autowired
    private PollService pollService;

    @Test
    public void shouldCreatePoll() {
        //given
        Long topicId = 1L;
        ZonedDateTime end = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll poll = new Poll(null, topicId, "description", end);

        given(topicRepository.existsById(any())).willReturn(true);

        //when
        pollService.create(poll);

        //then
        verify(topicRepository).existsById(topicId);
        verify(pollRepository).save(poll);
    }

    @Test
    public void shouldRejectPollWithoutATopicId() {
        //given
        ZonedDateTime end = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll poll = new Poll(null, null, "description", end);

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                pollService.create(poll), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("topicId is mandatory");
    }

    @Test
    public void shouldRejectPollWhenTopicDoesNotExist() {
        //given
        ZonedDateTime end = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll poll = new Poll(null, 1L, "description", end);

        given(topicRepository.existsById(any())).willReturn(false);

        //when
        DataIntegrityException thrown = catchThrowableOfType(() ->
                pollService.create(poll), DataIntegrityException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Topic with id=1 not found");
    }

    @Test
    public void shouldRejectPollWithAnEmptyDescription() {
        //given
        ZonedDateTime end = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll poll = new Poll(null, 1L, "", end);

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                pollService.create(poll), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("description is mandatory");
    }

    @Test
    public void shouldSetPollDurationToOneMinuteIfEndDateWasNotInformed() {
        //given
        Poll poll = new Poll(null, 1L, "description", null);
        ZonedDateTime now = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll defaultEndingPoll = new Poll(null, 1L, "description", now.plus(Duration.ofMinutes(1)));

        given(topicRepository.existsById(any())).willReturn(true);

        when(clock.now()).thenReturn(now);

        //when
        pollService.create(poll);

        //then
        verify(pollRepository).save(defaultEndingPoll);
    }
}