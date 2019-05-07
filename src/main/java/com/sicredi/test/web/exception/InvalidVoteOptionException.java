package com.sicredi.test.web.exception;

/**
 * Exception for invalid vote option.
 */
public class InvalidVoteOptionException extends TopicApplicationException {

	private static final long serialVersionUID = -3571609521821778747L;
	
	public InvalidVoteOptionException() {
		super("Invalid vote option!");
	}
}
