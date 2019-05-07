package com.sicredi.test.web.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sicredi.test.persistence.model.Poll;
import com.sicredi.test.web.dto.PollDto;

/**
 * {@link PollDto} to {@link Poll} converter
 */
@Component
public class PollDtoToPollConverter {

	@Value("${poll.durationInMinutes.default:1}")
	private int defautlPollDurationInMinutes;

	public Poll convert(PollDto pollDto) {
		return (pollDto == null || pollDto.getDurationInMinutes() == 0)
				? new Poll(defautlPollDurationInMinutes)
				: new Poll(pollDto.getDurationInMinutes()); 
	}
}
