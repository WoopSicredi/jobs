package com.sicredi.test.web.exception;

/**
 * Exception quando uma pauta invalida é referenciada.
 */
public class InvalidTopicException extends TopicApplicationException {

    private static final long serialVersionUID = -3571609521821778747L;

    public InvalidTopicException() {
        super("Invalid topic reference");
    }
}
