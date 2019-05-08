package com.sicredi.test.web.exception;

/**
 * Exception quando um usuário tenta votar mais de uma vez em uma determinda
 * pauta.
 */
public class UserAlreadyVoteException extends TopicApplicationException {

    private static final long serialVersionUID = -4438354556490435197L;

    public UserAlreadyVoteException() {
        super("User already vote on this topic");
    }
}
