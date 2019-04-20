package com.vollino.poll.service.exception.rest.handler;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponseBody that = (ErrorResponseBody) o;
        return Objects.equals(getErrors(), that.getErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getErrors());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errors", errors)
                .toString();
    }
}
