package com.vollino.poll.service.exception.handler;

import com.vollino.poll.service.exception.ClientErrorException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Bruno Vollino
 */
@RunWith(JUnit4.class)
public class ClientErrorExceptionHandlerTest {

    private ClientErrorExceptionHandler clientErrorExceptionHandler;

    @Before
    public void setUp() {
        clientErrorExceptionHandler = new ClientErrorExceptionHandler();
    }

    @Test
    public void shouldHandleException() {
        //given
        String errorMessage = "Error message";
        ClientErrorException clientErrorException = new ClientErrorException(errorMessage);
        ResponseEntity<ErrorResponseBody> expected = ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new ErrorResponseBody().withError(errorMessage));

        //when
        ResponseEntity<ErrorResponseBody> actual =
                clientErrorExceptionHandler.handleException(clientErrorException);

        //then
        assertThat(actual, equalTo(expected));
    }
}