package com.sicredi.test.web.dto;

import com.sicredi.test.persistence.model.VoteOption;

public class VoteDto {

	private String username;
	private VoteOption voteOption;

	public VoteDto() {}

	public VoteDto(long topicId, String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public VoteOption getVoteOption() {
		return voteOption;
	}
	
	public void setVoteOption(VoteOption voteOption) {
		this.voteOption = voteOption;
	}
}
