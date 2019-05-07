package com.sicredi.test.web.dto;

public class TopicDto {

	private long topicId;
	private PollDto poll;

	public TopicDto() {}

	public TopicDto(long id, PollDto poll) {
		this.topicId = id;
		this.poll = poll;
	}

	public long getTopicId() {
		return topicId;
	}
	
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	public PollDto getPoll() {
		return poll;
	}

	public void setPoll(PollDto poll) {
		this.poll = poll;
	}
}
