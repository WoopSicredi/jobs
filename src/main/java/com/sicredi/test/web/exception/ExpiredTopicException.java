package com.sicredi.test.web.exception;

public class ExpiredTopicException extends TopicApplicationException {

	private static final long serialVersionUID = 6671321188176496318L;

	public ExpiredTopicException() {
		super("This topic is open do not accept votes anymore");
	}
}
