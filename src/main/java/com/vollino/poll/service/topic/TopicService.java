package com.vollino.poll.service.topic;

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

    @Validated
    public Topic create(@Valid Topic topic) {
        return topicRepository.save(topic);
    }
}
