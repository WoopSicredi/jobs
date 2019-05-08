package com.sicredi.test.web.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.web.dto.PollCreationDto;

/**
 * Conversor de {@link PollCreationDto} para {@link Poll}.
 */
@Component
public class PollDtoToPollConverter {

    @Value("${poll.durationInMinutes.default:1}")
    private int defautlPollDurationInMinutes;

    public Poll convert(PollCreationDto pollDto) {
        return (pollDto == null || pollDto.getDurationInMinutes() == 0) ? new Poll(defautlPollDurationInMinutes)
                : new Poll(pollDto.getDurationInMinutes());
    }
}
