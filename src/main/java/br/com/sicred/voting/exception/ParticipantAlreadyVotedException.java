package br.com.sicred.voting.exception;

public class ParticipantAlreadyVotedException extends RuntimeException{
    public ParticipantAlreadyVotedException() {
        super("O participante já votou nesta seção");
    }
}
