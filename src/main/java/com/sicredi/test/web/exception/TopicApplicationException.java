package com.sicredi.test.web.exception;

/**
 * Generic exception containing a error message.  
 */
public class TopicApplicationException extends RuntimeException {

	private static final long serialVersionUID = -4351439784014002596L;

	public TopicApplicationException(String message) {
		super(message);
	}
}
