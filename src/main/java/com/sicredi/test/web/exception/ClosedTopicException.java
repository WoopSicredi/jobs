package com.sicredi.test.web.exception;

/**
 * Exception when a topic no longer accept votes.
 */
public class ClosedTopicException extends TopicApplicationException {

	private static final long serialVersionUID = -50663244808558806L;

	public ClosedTopicException() {
		super("This topic do not accept votes anymore");
	}
}
