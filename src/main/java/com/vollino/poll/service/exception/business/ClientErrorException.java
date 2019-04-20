package com.vollino.poll.service.exception.business;

/**
 * @author Bruno Vollino
 */
public class ClientErrorException extends RuntimeException {

    public ClientErrorException(String message) {
        super(message);
    }

    public ClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
