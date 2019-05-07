package com.sicredi.test.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PollResultDto {

	private long topicId;
	
	private List<VoteCountDto> votes = new ArrayList<>();
	
	public PollResultDto() {}
	
	public PollResultDto(long topicId) {
		this.topicId = topicId;
	}
	
	public long getTopicId() {
		return topicId;
	}

	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public List<VoteCountDto> getVotes() {
		return votes;
	}
	
	public void setVotes(List<VoteCountDto> votes) {
		this.votes = votes;
	}

}
