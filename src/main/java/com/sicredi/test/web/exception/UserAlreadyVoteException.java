package com.sicredi.test.web.exception;

/**
 * Exception when a user has already vote on a topic.
 */
public class UserAlreadyVoteException extends TopicApplicationException {

	private static final long serialVersionUID = -4438354556490435197L;

	public UserAlreadyVoteException() {
		super("User already vote on this topic");
	}
}
