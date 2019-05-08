package com.sicredi.test.web.converter;

import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.web.dto.PollDto;
import com.sicredi.test.web.dto.TopicDto;

/**
 * Conversor de {@link Poll} para {@link PollDto}.
 */
@Component
public class PollToPollDtoConverter {

    public PollDto convert(Poll poll) {
        return new PollDto(new TopicDto(poll.getTopic().getId(), poll.getTopic().getName()), poll.getCreatedOn(),
                poll.getDurationInMinutes());
    }

}
