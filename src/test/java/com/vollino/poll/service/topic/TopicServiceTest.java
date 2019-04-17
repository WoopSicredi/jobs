package com.vollino.poll.service.topic;

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
    public void shouldNotCreateTopicWithAnEmptyDescription() {
        //given
        Topic topic = new Topic(null, "");

        //when
        ConstraintViolationException thrown = catchThrowableOfType(() -> topicService.create(topic), ConstraintViolationException.class);

        //then
        assertThat(thrown).isNotNull();
        assertThat(thrown.getConstraintViolations().iterator().next().getMessage())
                .isEqualTo("Topic description is mandatory");
    }
}