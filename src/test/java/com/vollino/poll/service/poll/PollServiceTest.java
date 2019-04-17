package com.vollino.poll.service.poll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
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
    private Clock clock;

    @Autowired
    private PollService pollService;

    @Test
    public void shouldCreatePoll() {
        //given
        ZonedDateTime end = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll poll = new Poll(null, 1L, "description", end);

        //when
        pollService.create(poll);

        //then
        verify(pollRepository).save(poll);
    }

    @Test
    public void shouldRejectTopicWithoutATopicId() {
        //given
        ZonedDateTime end = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll poll = new Poll(null, null, "description", end);

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                pollService.create(poll), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("Topic ID is mandatory");
    }

    @Test
    public void shouldRejectTopicWithAnEmptyDescription() {
        //given
        ZonedDateTime end = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll poll = new Poll(null, 1L, "", end);

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                pollService.create(poll), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("Poll description is mandatory");
    }

    @Test
    public void shouldSetCurrentServerTimeAsEndDateIfItWasNotInformed() {
        //given
        Poll poll = new Poll(null, 1L, "description", null);
        ZonedDateTime now = ZonedDateTime.parse("2019-04-17T02:23:00-09:00[US/Alaska]");
        Poll pollWithGeneratedEnd = new Poll(null, 1L, "description", now);

        when(clock.now()).thenReturn(now);

        //when
        pollService.create(poll);

        //then
        verify(pollRepository).save(pollWithGeneratedEnd);
    }
}