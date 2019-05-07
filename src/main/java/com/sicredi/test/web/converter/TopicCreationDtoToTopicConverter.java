package com.sicredi.test.web.converter;

import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Topic;
import com.sicredi.test.web.dto.TopicCreationDto;

@Component
public class TopicCreationDtoToTopicConverter {

	public Topic convert(TopicCreationDto topicCreationDto) {
		return new Topic(topicCreationDto.getName());
	}
}
