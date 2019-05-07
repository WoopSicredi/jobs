package com.sicredi.test.web.exception;

/**
 * Exception for invalid topics references.
 */
public class InvalidTopicException extends TopicApplicationException {

	private static final long serialVersionUID = -3571609521821778747L;
	
	public InvalidTopicException() {
		super("Invalid topic reference");
	}
}
