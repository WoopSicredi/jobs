package com.sicredi.test.web.exception;

public final class MyResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6288440113215911752L;

	public MyResourceNotFoundException() {
        super();
    }

    public MyResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MyResourceNotFoundException(final String message) {
        super(message);
    }

    public MyResourceNotFoundException(final Throwable cause) {
        super(cause);
    }

}
