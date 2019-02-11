package br.com.sicred.voting.exception;

public class VotingSessionStillOpenException extends RuntimeException {
    public VotingSessionStillOpenException() {
        super("A sessão de votação ainda está aberta");
    }
}
