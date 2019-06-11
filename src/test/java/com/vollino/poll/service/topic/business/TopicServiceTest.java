package com.vollino.poll.service.topic.business;

import com.vollino.poll.service.topic.business.domain.Topic;
import com.vollino.poll.service.topic.persistence.TopicRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Bruno Vollino
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ValidationAutoConfiguration.class, TopicService.class})
public class TopicServiceTest {

    @MockBean
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @Test
    public void shouldCreateTopic() {
        //given
        Topic topic = new Topic(null, "description");

        //when
        topicService.create(topic);

        //then
        verify(topicRepository).save(topic);
    }

    @Test
    public void shouldRejectTopicWithAnEmptyDescription() {
        //given
        Topic topic = new Topic(null, "");

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() ->
                topicService.create(topic), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("description is mandatory");
    }

    @Test
    public void shouldGetAllTopics() {
        //given
        List<Topic> expected = Lists.newArrayList(mock(Topic.class), mock(Topic.class));

        given(topicRepository.findAll()).willReturn(expected);

        //when
        List<Topic> actual = topicService.getAll();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}