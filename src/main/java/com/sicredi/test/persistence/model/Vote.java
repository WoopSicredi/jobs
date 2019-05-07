package com.sicredi.test.persistence.model;

public class Vote {

	private String username;
	private VoteOption voteOption;

	public Vote() {}

	public Vote(long topicId, String username) {
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
