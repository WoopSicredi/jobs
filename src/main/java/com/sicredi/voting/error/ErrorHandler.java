package com.sicredi.voting.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sicredi.voting.exception.SessionAlreadyOpenException;
import com.sicredi.voting.exception.SessionClosedException;
import com.sicredi.voting.exception.SessionNotFoundException;
import com.sicredi.voting.exception.SessionNotStartedException;
import com.sicredi.voting.exception.SessionStillOpenException;
import com.sicredi.voting.exception.UserAlreadyVotedThisTopicException;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(SessionAlreadyOpenException.class)
	public final ResponseEntity<ErrorResponse> handleSessionAlreadyOpenException(SessionAlreadyOpenException ex) {
		return handleError("Sessão já está aberta!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SessionClosedException.class)
	public final ResponseEntity<ErrorResponse> SessionClosedException(SessionClosedException ex) {
		return handleError("Sessão já está fechada!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SessionNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleSessionNotFoundException(SessionNotFoundException ex) {
		return handleError("Sessão não encontrada!", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SessionNotStartedException.class)
	public final ResponseEntity<ErrorResponse> handleSessionNotStartedException(SessionNotStartedException ex) {
		return handleError("Sessão ainda não começou!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SessionStillOpenException.class)
	public final ResponseEntity<ErrorResponse> handleSessionStillOpenException(SessionStillOpenException ex) {
		return handleError("Sessão ainda está aberta!", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAlreadyVotedThisTopicException.class)
	public final ResponseEntity<ErrorResponse> handleUserAlreadyVotedThisTopicException(UserAlreadyVotedThisTopicException ex) {
		return handleError("Usuário já votou nesse tópico!", HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> handleError(String message, HttpStatus status) {
		return new ResponseEntity<>(new ErrorResponse(message), status);
	}
}
