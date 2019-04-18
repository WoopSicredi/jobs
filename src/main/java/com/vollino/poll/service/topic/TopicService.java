package com.vollino.poll.service.topic;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author Bruno Vollino
 */
@Service
@Validated
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic create(@Valid Topic topic) {
        Preconditions.checkArgument(topic.getId() == null, "A new Topic must have no ID on creation");

        return topicRepository.save(topic);
    }
}
