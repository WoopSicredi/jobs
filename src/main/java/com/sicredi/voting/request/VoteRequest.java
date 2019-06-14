package com.sicredi.voting.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class VoteRequest {

	@NotNull
	@Positive
	private Long topicId;
	
	@NotNull
	@Positive
	private Long userId;
	
	@NotNull
	private Boolean value;

	
	public Long getTopicId() {
		return topicId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Boolean getValue() {
		return value;
	}
}
