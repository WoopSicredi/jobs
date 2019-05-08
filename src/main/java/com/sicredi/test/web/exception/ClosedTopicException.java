package com.sicredi.test.web.exception;

/**
 * Exception quando uma pauta ainda não foi aberta para votação.
 */
public class ClosedTopicException extends TopicApplicationException {

    private static final long serialVersionUID = -50663244808558806L;

    public ClosedTopicException() {
        super("This topic is not open yet");
    }
}
