package com.sicredi.voting.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OpenSessionRequest {

	@NotNull
	@Positive
	private Long topicId;
	
	private Long durationInMinutes;

	
	public Long getTopicId() {
		return topicId;
	}
	
	public Long getDurationInMinutes() {
		return durationInMinutes;
	}
}
