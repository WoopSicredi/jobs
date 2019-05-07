package com.sicredi.test.web.exception;

public class PollAlreadyCreatedException extends TopicApplicationException {

	private static final long serialVersionUID = 6300963048356770465L;

	public PollAlreadyCreatedException() {
		super("Poll is already created on this topic");
	}
}
