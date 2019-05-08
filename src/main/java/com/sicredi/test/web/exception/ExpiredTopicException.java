package com.sicredi.test.web.exception;

/**
 * Exception quando uma pauta já está expirada para votação.
 */
public class ExpiredTopicException extends TopicApplicationException {

    private static final long serialVersionUID = 6671321188176496318L;

    public ExpiredTopicException() {
        super("This topic do not accept votes anymore");
    }
}
