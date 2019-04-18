package com.vollino.poll.service.exception.handler;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Bruno Vollino
 */
@RunWith(JUnit4.class)
public class ConstraintViolationExceptionHandlerTest {

    private ConstraintViolationExceptionHandler constraintViolationExceptionHandler;

    @Before
    public void setUp() {
        constraintViolationExceptionHandler = new ConstraintViolationExceptionHandler();
    }

    @Test
    public void shouldHandleException() {
        //given
        String errorMessage1 = "Error message 1";
        String errorMessage2 = "Error message 2";
        ConstraintViolation<String> violation1 = mock(ConstraintViolation.class);
        ConstraintViolation<String> violation2 = mock(ConstraintViolation.class);
        ConstraintViolationException exception = new ConstraintViolationException(
                "Exception thrown", ImmutableSet.of(violation1, violation2));

        ResponseEntity<ErrorResponseBody> expected = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new ErrorResponseBody().withError(errorMessage1).withError(errorMessage2));

        given(violation1.getMessage()).willReturn(errorMessage1);
        given(violation2.getMessage()).willReturn(errorMessage2);

        //when
        ResponseEntity<ErrorResponseBody> actual =
                constraintViolationExceptionHandler.handleException(exception);

        //then
        assertThat(actual, equalTo(expected));
    }
}