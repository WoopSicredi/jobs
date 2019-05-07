package com.sicredi.test.web.exception;

public class OpenTopicException extends TopicApplicationException {

	private static final long serialVersionUID = -7895532073110183381L;

	public OpenTopicException() {
		super("Topic is not closed yet");
	}
}
