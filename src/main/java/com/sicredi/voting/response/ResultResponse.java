package com.sicredi.voting.response;

public class ResultResponse {

	private String topic;
	private Boolean passed;
	
	
	public ResultResponse(String topic, Boolean passed) {
		this.topic = topic;
		this.passed = passed;
	}

	public String getTopic() {
		return topic;
	}

	public Boolean getPassed() {
		return passed;
	}
}