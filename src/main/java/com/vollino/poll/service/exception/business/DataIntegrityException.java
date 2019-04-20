package com.vollino.poll.service.exception.business;

/**
 * @author Bruno Vollino
 */
public class DataIntegrityException extends ClientErrorException {

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
