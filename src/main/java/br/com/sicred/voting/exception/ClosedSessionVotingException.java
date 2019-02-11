package br.com.sicred.voting.exception;

public class ClosedSessionVotingException extends RuntimeException {

    public ClosedSessionVotingException() {
        super("Sessão de votação encerrada");
    }
}
