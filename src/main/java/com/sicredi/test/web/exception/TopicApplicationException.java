package com.sicredi.test.web.exception;

/**
 * Exception genérica para englobar todos os erros da aplicação.
 */
public class TopicApplicationException extends RuntimeException {

    private static final long serialVersionUID = -4351439784014002596L;

    public TopicApplicationException(String message) {
        super(message);
    }
}
