package com.sicredi.test.web.exception;

/**
 * Exception quando uma pauta ainda não fechou a votação e não pode emitir
 * resultados.
 */
public class OpenTopicException extends TopicApplicationException {

    private static final long serialVersionUID = -7895532073110183381L;

    public OpenTopicException() {
        super("Topic is not closed yet");
    }
}
