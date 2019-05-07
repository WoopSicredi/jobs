package com.sicredi.test.web.dto;

public class TopicDto {

	private long topicId;
	private String topicName;

	public TopicDto() {}

	public TopicDto(long id, String topicName) {
		this.topicId = id;
		this.topicName = topicName;
	}

	public long getTopicId() {
		return topicId;
	}
	
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}
	
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}
