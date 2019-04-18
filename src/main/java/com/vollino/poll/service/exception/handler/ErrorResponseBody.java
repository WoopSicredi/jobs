package com.vollino.poll.service.exception.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Vollino
 */
public class ErrorResponseBody {

    private List<String> errors;

    public ErrorResponseBody() {
        this.errors = new ArrayList<>();
    }

    public ErrorResponseBody withError(String error) {
        errors.add(error);

        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
