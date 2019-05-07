package com.sicredi.test.web.converter;

import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.web.dto.TopicDto;

@Component
public class TopicToTopicDtoConverter {

	public TopicDto convert(Topic topic) {
		return new TopicDto(topic.getId(), topic.getName());
	}
}
